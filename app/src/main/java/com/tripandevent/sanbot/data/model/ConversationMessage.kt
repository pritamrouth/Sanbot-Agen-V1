package com.tripandevent.sanbot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConversationMessage(
    val id: String,
    val role: MessageRole,
    val text: String,
    val audioUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

enum class MessageRole {
    USER, AGENT
}
