package com.tripandevent.sanbot.data.model

<<<<<<< Updated upstream
import android.os.Parcel
import android.os.Parcelable

data class ContactSubmission(
    val name: String,
    val phone: String,
    val countryCode: String,
    val email: String?,
    val selectedPackageIds: List<String>,
    val notes: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString() ?: "",
        phone = parcel.readString() ?: "",
        countryCode = parcel.readString() ?: "",
        email = parcel.readString(),
        selectedPackageIds = parcel.createStringArrayList() ?: emptyList(),
        notes = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(countryCode)
        parcel.writeString(email)
        parcel.writeStringList(selectedPackageIds)
        parcel.writeString(notes)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ContactSubmission> {
        override fun createFromParcel(parcel: Parcel): ContactSubmission {
            return ContactSubmission(parcel)
        }

        override fun newArray(size: Int): Array<ContactSubmission?> {
            return arrayOfNulls(size)
        }
    }
}
=======
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
>>>>>>> Stashed changes
