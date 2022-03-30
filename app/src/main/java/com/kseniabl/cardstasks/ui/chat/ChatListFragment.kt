package com.kseniabl.cardstasks.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardtasks.ui.chat.ChatListView
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
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

}