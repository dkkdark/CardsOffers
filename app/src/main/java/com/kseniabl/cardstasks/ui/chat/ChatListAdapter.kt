package com.kseniabl.cardstasks.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kseniabl.cardtasks.R
import com.mikhaellopez.circularimageview.CircularImageView
import javax.inject.Inject

class ChatListAdapter @Inject constructor(var presenter: ChatListPresenter<ChatListView, ChatListInteractorInterface>, var context: Context, var fragment: ChatListFragment): RecyclerView.Adapter<ChatListAdapter.ChatListHolder>() {

    fun addElement(el: ChatWithModel, pos: Int) {
        presenter.addElementToList(el, pos)
        notifyDataSetChanged()
    }

    fun getElements(): List<ChatWithModel> {
        return presenter.getAllElements()
    }

    fun removeAllInList(list: List<ChatWithModel>) {
        presenter.removeAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListHolder {
        presenter.attachView(fragment)
        return ChatListHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ChatListHolder, position: Int) {
        presenter.onBindItemView(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        presenter.detachView()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class ChatListHolder(view: View): RecyclerView.ViewHolder(view), ItemViewChatWithModel {
        private val profileImage: CircularImageView = view.findViewById(R.id.profileImageChatList)
        private val profileName: TextView = view.findViewById(R.id.profileNameChatList)
        private val profileLastMessage: TextView = view.findViewById(R.id.profileLastMessageChatList)
        private val notSeenMsgs: TextView = view.findViewById(R.id.notSeenMessagesChatList)
        private val specializationChatText: TextView = view.findViewById(R.id.specializationChatText)
        private val costChatText: TextView = view.findViewById(R.id.costChatText)

        init {
            view.setOnClickListener {
                notSeenMsgs.text = "0"
                notSeenMsgs.visibility = View.GONE
                presenter.onItemClicked(adapterPosition)
            }
        }

        override fun bindItem(item: ChatWithModel) {
            profileName.text = item.username
            profileLastMessage.text = item.lastMessage
            specializationChatText.text = item.card_title
            costChatText.text = "${item.card_cost} $"
            if (item.notSeenMessages != 0) {
                notSeenMsgs.text = item.notSeenMessages.toString()
                notSeenMsgs.visibility = View.VISIBLE
            }
            else
                notSeenMsgs.visibility = View.GONE
        }
    }
}