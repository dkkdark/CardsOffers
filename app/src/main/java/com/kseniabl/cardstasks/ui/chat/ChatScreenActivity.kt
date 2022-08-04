package com.kseniabl.cardstasks.ui.chat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.R
import javax.inject.Inject

import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.databinding.ActivityMainBinding
import com.kseniabl.cardtasks.databinding.FragmentChatScreenBinding
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ChatScreenActivity: BaseActivity(), ChatScreenView {

    @Inject
    lateinit var presenter: ChatScreenPresenterInterface<ChatScreenView>
    @Inject
    lateinit var currentUserClass: CurrentUserClass

    @Inject
    lateinit var repository: RepositoryInterface

    private var isCardExist = true

    private var id: String? = null
    private var card_id: String? = null
    private var card_title: String? = null
    private var card_cost: String? = null

    private lateinit var binding: FragmentChatScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChatScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)

        id = intent.extras?.getString("id")
        card_id = intent.extras?.getString("card_id")
        card_title = intent.extras?.getString("card_title")
        card_cost = intent.extras?.getString("card_cost")

        checkIfTaskDeleted()

        if (id != null && card_id != null && card_title != null && card_cost != null) {
            CardTasksUtils.setCardId(card_id!!)
            setPreviousMessagesAndData()
            setupSendMessageListener()
            presenter.loadReceived(id!!, card_id!!, binding.chatView, this)
        }

    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun checkIfTaskDeleted() {
        GlobalScope.launch {
            val cards = repository.allAddProdCards()
            for (card in cards) {
                if (card.id == card_id)
                    isCardExist = false
            }
        }

        if (currentUserClass.readSharedPref()?.id != id) {
            GlobalScope.launch {
                val allCards = repository.allCardsAll()
                for (oneListCards in allCards) {
                    for (card in oneListCards.cards) {
                        if (card.id == card_id)
                            isCardExist = false
                    }
                }
            }
        }
    }

    private fun setPreviousMessagesAndData() {
        binding.apply {
            cardTitleChatScreen.text = card_title
            cardCostChatScreen.text = "$card_cost $"
            presenter.setAllMessages(id!!, card_id!!, chatView)
        }
    }

    private fun setupSendMessageListener() {
        binding.chatView.setOnSentMessageListener {
            if (isCardExist) {
                Toast.makeText(this, "You cannot type to this task", Toast.LENGTH_SHORT)
                    .show()
                false
            }
            else {
                if (currentUserClass.readSharedPref() != null) {
                    presenter.sentMessageWithServer(currentUserClass.readSharedPref()!!.id, id!!, currentUserClass.readSharedPref()!!.username, it.message, card_id!!, card_title!!, card_cost!!)
                    presenter.insertMessage(id!!, card_id!!, it!!, binding.chatView, card_title!!, card_cost!!)
                    true
                }

                else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }
}