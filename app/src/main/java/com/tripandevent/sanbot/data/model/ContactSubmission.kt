package com.tripandevent.sanbot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactSubmission(
    val name: String,
    val phone: String,
    val countryCode: String = "+971",
    val email: String? = null,
    val selectedPackageIds: List<String> = emptyList(),
    val notes: String? = null,
    val preferredChannel: ContactChannel = ContactChannel.PHONE
) : Parcelable

enum class ContactChannel {
    PHONE, SMS, WHATSAPP, EMAIL
}