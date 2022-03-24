package com.kseniabl.cardstasks.ui.base

import co.intentservice.chatui.models.ChatMessage
import io.reactivex.rxjava3.subjects.PublishSubject

object MessagesContainer {

    private val messages = arrayListOf<ChatMessage>()
    private val isMessageAppear: PublishSubject<ChatMessage> = PublishSubject.create()

    fun addMessageToSender(message: String, time: Long) {
        val chatMessage = ChatMessage(message, time, ChatMessage.Type.SENT)
        messages.add(chatMessage)
    }

    fun addMessageToReceiver(message: String, time: Long) {
        val chatMessage = ChatMessage(message, time, ChatMessage.Type.RECEIVED)
        messages.add(chatMessage)
        isMessageAppear.onNext(chatMessage)
    }

    fun getMessages() = messages

    fun getIsMessageAppearObservable() = isMessageAppear
}