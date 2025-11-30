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
