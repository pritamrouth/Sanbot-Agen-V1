package com.tripandevent.sanbot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaAsset(
    val id: String,
    val type: MediaType,
    val title: String,
    val thumbnailUrl: String,
    val mediaUrl: String,
    val duration: String? = null,
    val category: String
) : Parcelable

enum class MediaType {
    IMAGE, VIDEO
}
