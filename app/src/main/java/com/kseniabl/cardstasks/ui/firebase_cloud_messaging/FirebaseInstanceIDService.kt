package com.kseniabl.cardstasks.ui.firebase_cloud_messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import co.intentservice.chatui.models.ChatMessage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kseniabl.cardstasks.db.db_models.ChatModel
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardstasks.ui.chat.ChatWithModel
import com.kseniabl.cardstasks.ui.splash.SplashScreenActivity
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.models.MessageModel
import dagger.android.AndroidInjection
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class FirebaseInstanceIDService: FirebaseMessagingService() {

    @Inject
    lateinit var messagesSaveAndLoad: MessageSaveAndLoadInterface
    @Inject
    lateinit var currentUserClass: CurrentUserClassInterface
    @Inject
    lateinit var chatListSaving: ChatListSavingInterface

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http:///192.168.1.64/")
            .build()
    }

    override fun onNewToken(token: String) {
        Log.e("qqq", "Refreshed token: $token")
        val retrofit = createRetrofit()
        currentUserClass.readSharedPref()?.id?.let {
            retrofit.create(RetrofitApiHolder::class.java).setToken(it, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MessageModel> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(data: MessageModel) {
                        if (data.message == "success") {
                            Log.e("qqq", "token setup succeed")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("qqq", "onNewToken onError ${e.message}")
                    }

                    override fun onComplete() {
                    }
                })
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            receiveMessage(remoteMessage)
        }
    }

    private fun receiveMessage(remoteMessage: RemoteMessage) {
        val cardId = remoteMessage.data["card_id"]!!

        if (CardTasksUtils.isAppIsInBackground(applicationContext) || (CardTasksUtils.getActivityRun() && !CardTasksUtils.getCardId(cardId)) || !CardTasksUtils.getActivityRun())
            sendNotification(remoteMessage)

        val id = remoteMessage.data["id"]!!
        messagesSaveAndLoad.setNewList(id, remoteMessage.data["card_id"]!!, ChatModel(message = remoteMessage.data["body"]!!, timestamp = remoteMessage.sentTime, type = ChatMessage.Type.RECEIVED))

        val list = arrayListOf<ChatWithModel>()
        var newEl: ChatWithModel? = null
        chatListSaving.getChatList()?.let { list.addAll(it) }

        val listToRemove = arrayListOf<ChatWithModel>()
        for (el in list) {
            if (el.id == id && el.card_id == remoteMessage.data["card_id"]!!) {
                listToRemove.add(el)
                var notSeenMsg = 0
                if (!CardTasksUtils.getActivityRun() || (CardTasksUtils.getActivityRun() && !CardTasksUtils.getCardId(cardId)))
                    notSeenMsg = el.notSeenMessages + 1
                newEl = ChatWithModel(el.id, el.username, remoteMessage.data["body"]!!, notSeenMsg, el.card_id, el.card_title, el.card_cost)
            }
        }
        list.removeAll(listToRemove)
        if (newEl != null) {
            list.add(newEl)
        }
        else {
            var notSeenMsg = 0
            if (!CardTasksUtils.getActivityRun() || (CardTasksUtils.getActivityRun() && !CardTasksUtils.getCardId(cardId)))
                notSeenMsg = 1
            list.add(ChatWithModel(id, remoteMessage.data["title"]!!, remoteMessage.data["body"]!!, notSeenMsg, remoteMessage.data["card_id"]!!, remoteMessage.data["card_title"]!!, remoteMessage.data["card_cost"]!!))
        }

        chatListSaving.saveChatList(list)
        notificationHandler.notificationHandle()
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val intent = Intent(this, SplashScreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0, intent, PendingIntent.FLAG_IMMUTABLE
        )
        val channelId = resources.getString(R.string.channel_tag)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.chat)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["body"])
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build());
    }

    companion object {
        var notificationHandler = NotificationHandler()
    }
}