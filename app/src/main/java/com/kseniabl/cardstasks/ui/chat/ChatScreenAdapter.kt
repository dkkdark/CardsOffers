/*

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.avatar.AvatarView
import android.text.format.DateFormat
import javax.inject.Inject

class ChatScreenAdapter @Inject constructor(var presenter: ChatScreenPresenter<ChatScreenView>, var context: Context, var fragment: ChatScreenFragment): RecyclerView.Adapter<ChatScreenAdapter.ChatScreenHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatScreenHolder {
        presenter.attachView(fragment)
        return ChatScreenHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_user, parent, false))
    }

    override fun onBindViewHolder(holder: ChatScreenHolder, position: Int) {
        presenter.onBindItemView(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    inner class ChatScreenHolder(view: View): RecyclerView.ViewHolder(view), ItemViewUserModel {
        private val chatName: TextView = view.findViewById(R.id.usernameTextItem)
        private val chatDate: TextView = view.findViewById(R.id.lastActiveTextItem)
        private val chatAvatar: AvatarView = view.findViewById(R.id.avatarViewItem)

        override fun bindItem(item: User) {
            chatName.text = item.name
            chatDate.text = DateFormat.format("dd/MMM/yyyy hh:mm a", item.lastActive!!.time)
            chatAvatar.setUserData(item)
        }

    }
}
*/
