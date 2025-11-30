package com.tripandevent.sanbot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelPackage(
    val id: String,
    val title: String,
    val price: Double,
    val currency: String = "AED",
    val duration: String,
    val rating: Float,
    val reviewCount: Int,
    val description: String,
    val highlights: List<String>,
    val itinerary: List<ItineraryItem>,
    val inclusions: List<String>,
    val exclusions: List<String>,
    val heroImageUrl: String = "",
    val galleryUrls: List<String> = emptyList(),
    val category: String = ""
) : Parcelable

@Parcelize
data class ItineraryItem(
    val time: String,
    val activity: String
) : Parcelable

@Parcelize
data class MediaAsset(
    val id: String,
    val type: MediaType,
    val title: String,
    val url: String,
    val thumbnailUrl: String? = null,
    val duration: String? = null,
    val category: String = ""
) : Parcelable

enum class MediaType {
    VIDEO, IMAGE
}

@Parcelize
data class ConversationMessage(
    val id: String,
    val role: MessageRole,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

enum class MessageRole {
    USER, AGENT
}

@Parcelize
data class ContactSubmission(
    val name: String,
    val phone: String,
    val countryCode: String,
    val email: String? = null,
    val selectedPackageIds: List<String> = emptyList(),
    val notes: String? = null,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable
