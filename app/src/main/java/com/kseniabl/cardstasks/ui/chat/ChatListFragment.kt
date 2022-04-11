package com.kseniabl.cardstasks.ui.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.base.ChatListSavingInterface
import com.kseniabl.cardstasks.ui.base.NotificationHandler
import com.kseniabl.cardstasks.ui.firebase_cloud_messaging.FirebaseInstanceIDService
import kotlinx.android.synthetic.main.fragment_chat_list.*
import javax.inject.Inject
import javax.inject.Provider

class ChatListFragment: BaseFragment(), ChatListView {

    @Inject
    lateinit var presenter: ChatListPresenterInterface<ChatListView>
    @Inject
    lateinit var adapter: ChatListAdapter
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var chatListSaving: ChatListSavingInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        setNotificationListener()
        setupRecyclerView()
        presenter.setChatList()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        chatListRecyclerView.layoutManager = flexlayoutManager
        chatListRecyclerView.adapter = adapter
        chatListRecyclerView.setHasFixedSize(true)
        chatListRecyclerView.setItemViewCacheSize(20)
    }

    override fun provideAdapter(): ChatListAdapter {
        return adapter
    }

    private fun setNotificationListener() {
        FirebaseInstanceIDService.notificationHandler
            .setOnNotificationHandlerListener(object : NotificationHandler.OnNotificationHandlerListener {
                override fun onNotificationHandler() {
                    presenter.setChatList()
                }
            })

        ChatScreenInteractor.notificationHandler
            .setOnNotificationHandlerListener(object : NotificationHandler.OnNotificationHandlerListener {
                override fun onNotificationHandler() {
                    presenter.setChatList()
                }
            })
    }

    override fun startChatScreenActivity(id: String, card_id: String, card_title: String, card_cost: String) {
        val intent = Intent(activity, ChatScreenActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("card_id", card_id)
        intent.putExtra("card_title", card_title)
        intent.putExtra("card_cost", card_cost)
        startActivity(intent)
    }

}