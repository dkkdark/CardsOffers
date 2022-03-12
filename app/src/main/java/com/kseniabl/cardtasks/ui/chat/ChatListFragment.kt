package com.kseniabl.cardtasks.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseFragment
import javax.inject.Inject

class ChatListFragment: BaseFragment(), ChatListView {

    @Inject
    lateinit var presenter: ChatListPresenterInterface<ChatListView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): ChatListFragment = ChatListFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

}