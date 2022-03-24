package com.kseniabl.cardstasks.ui.firebase_cloud_messaging

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.models.MessageModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.MessageSaveAndLoadInterface
import com.kseniabl.cardstasks.ui.base.MessagesContainer
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardstasks.ui.splash.SplashScreenActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class FirebaseInstanceIDService: FirebaseMessagingService() {

    @Inject
    lateinit var messagesSaveAndLoad: MessageSaveAndLoadInterface
    @Inject
    lateinit var currentUserClass: CurrentUserClass

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://10.0.2.2:5000/")
            .build()
    }

    override fun onNewToken(token: String) {
        Log.e("qqq", "Refreshed token: $token")
        val retrofit = createRetrofit()
        val id = currentUserClass.readSharedPref().id
        retrofit.create(RetrofitApiHolder::class.java).setToken(id, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "token setup succeed")
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "onNewToken onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            receiveMessage(remoteMessage)
        }
    }

    private fun receiveMessage(remoteMessage: RemoteMessage) {
        if (CardTasksUtils.isAppIsInBackground(applicationContext))
            sendNotification(remoteMessage)
        MessagesContainer.addMessageToReceiver(remoteMessage.data["body"]!!, remoteMessage.sentTime)

        val id = currentUserClass.readSharedPref().id
        Log.e("qqq", "id = $id")
        messagesSaveAndLoad.setNewList(id, ChatModel(message = remoteMessage.data["body"]!!, timestamp = remoteMessage.sentTime, type = ChatMessage.Type.RECEIVED))
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
}