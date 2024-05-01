package ru.yapracticum.sprint10koh16

import java.util.Date


sealed class ChatMessage {
    abstract val id: String
    abstract val date: Date
    abstract val text: String

    data class MyChatMessage(
        override val id: String,
        override val date: Date,
        override val text: String,
        val status: MessageStatus,
    ) : ChatMessage()
    data class OtherChatMessage(
        override val id: String,
        override val date: Date,
        override val text: String,
    ) : ChatMessage()

    data class ChatDate(
        override val id: String,
        override val date: Date,
        override val text: String,
    ) : ChatMessage()

}

enum class MessageStatus {
    NEW, SENT, READ
}