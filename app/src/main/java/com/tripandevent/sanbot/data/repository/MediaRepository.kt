package com.tripandevent.sanbot.data.repository

import com.tripandevent.sanbot.data.model.MediaAsset
import com.tripandevent.sanbot.data.model.MediaType

object MediaRepository {
    
    fun getAllMedia(): List<MediaAsset> = sampleMedia
    
    fun getVideos(): List<MediaAsset> = sampleMedia.filter { it.type == MediaType.VIDEO }
    
    fun getImages(): List<MediaAsset> = sampleMedia.filter { it.type == MediaType.IMAGE }
    
    fun getMediaByCategory(category: String): List<MediaAsset> =
        sampleMedia.filter { it.category.equals(category, ignoreCase = true) }
    
    private val sampleMedia = listOf(
        MediaAsset(
            id = "v1",
            type = MediaType.VIDEO,
            title = "Dubai Highlights",
            thumbnailUrl = "",
            mediaUrl = "",
            duration = "2:30",
            category = "Dubai"
        ),
        MediaAsset(
            id = "v2",
            type = MediaType.VIDEO,
            title = "Desert Safari Experience",
            thumbnailUrl = "",
            mediaUrl = "",
            duration = "3:15",
            category = "Dubai"
        ),
        MediaAsset(
            id = "v3",
            type = MediaType.VIDEO,
            title = "Abu Dhabi Tour",
            thumbnailUrl = "",
            mediaUrl = "",
            duration = "4:00",
            category = "Abu Dhabi"
        ),
        MediaAsset(
            id = "i1",
            type = MediaType.IMAGE,
            title = "Burj Khalifa at Night",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Dubai"
        ),
        MediaAsset(
            id = "i2",
            type = MediaType.IMAGE,
            title = "Dubai Marina",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Dubai"
        ),
        MediaAsset(
            id = "i3",
            type = MediaType.IMAGE,
            title = "Desert Sunset",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Dubai"
        ),
        MediaAsset(
            id = "i4",
            type = MediaType.IMAGE,
            title = "Sheikh Zayed Mosque",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Abu Dhabi"
        ),
        MediaAsset(
            id = "i5",
            type = MediaType.IMAGE,
            title = "Musandam Fjords",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Oman"
        ),
        MediaAsset(
            id = "i6",
            type = MediaType.IMAGE,
            title = "Palm Jumeirah",
            thumbnailUrl = "",
            mediaUrl = "",
            category = "Dubai"
        )
    )
}
