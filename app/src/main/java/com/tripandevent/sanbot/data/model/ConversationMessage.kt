package com.tripandevent.sanbot.data.model

data class ConversationMessage(
    val id: String,
    val role: MessageRole,
    val text: String,
    val audioUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

enum class MessageRole {
    USER, AGENT
}
