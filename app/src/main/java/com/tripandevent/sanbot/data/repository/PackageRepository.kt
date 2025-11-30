package com.tripandevent.sanbot.data.repository

import com.tripandevent.sanbot.data.model.ItineraryItem
import com.tripandevent.sanbot.data.model.TravelPackage

object PackageRepository {
    
    fun getAllPackages(): List<TravelPackage> = samplePackages
    
    fun getPackageById(id: String): TravelPackage? = samplePackages.find { it.id == id }
    
    fun getPackagesByCategory(category: String): List<TravelPackage> = 
        samplePackages.filter { it.category.equals(category, ignoreCase = true) }
    
    private val samplePackages = listOf(
        TravelPackage(
            id = "1",
            title = "Dubai Desert Safari",
            price = 299.0,
            currency = "AED",
            duration = "6 hours",
            rating = 4.8f,
            reviewCount = 234,
            description = "Experience the thrill of the Arabian desert with our premium desert safari package. Enjoy dune bashing in a 4x4 vehicle, camel riding, sandboarding, and a delicious BBQ dinner under the stars.",
            highlights = listOf(
                "Thrilling dune bashing",
                "Camel ride experience",
                "Traditional BBQ dinner",
                "Belly dance show",
                "Henna painting",
                "Sunset photography"
            ),
            itinerary = listOf(
                ItineraryItem("15:00", "Hotel pickup"),
                ItineraryItem("16:00", "Dune bashing adventure"),
                ItineraryItem("17:30", "Camel riding & sandboarding"),
                ItineraryItem("18:30", "Sunset photography"),
                ItineraryItem("19:00", "BBQ dinner & entertainment"),
                ItineraryItem("21:00", "Return to hotel")
            ),
            inclusions = listOf(
                "Hotel pickup & drop-off",
                "4x4 Land Cruiser",
                "Professional driver",
                "BBQ dinner with unlimited beverages",
                "Entertainment shows"
            ),
            exclusions = listOf(
                "Personal expenses",
                "Quad biking (optional)",
                "Professional photography"
            ),
            heroImageUrl = "",
            galleryUrls = listOf(),
            category = "Dubai"
        ),
        TravelPackage(
            id = "2",
            title = "Burj Khalifa Tour",
            price = 199.0,
            currency = "AED",
            duration = "3 hours",
            rating = 4.9f,
            reviewCount = 456,
            description = "Visit the world's tallest building and enjoy breathtaking views of Dubai from the observation deck on the 124th and 125th floors.",
            highlights = listOf(
                "At The Top observation deck",
                "360-degree city views",
                "Multimedia presentation",
                "World's highest outdoor observation deck"
            ),
            itinerary = listOf(
                ItineraryItem("09:00", "Meet at Dubai Mall entrance"),
                ItineraryItem("09:15", "High-speed elevator ride"),
                ItineraryItem("09:30", "Observation deck experience"),
                ItineraryItem("11:00", "Free time at Dubai Mall"),
                ItineraryItem("12:00", "End of tour")
            ),
            inclusions = listOf(
                "Skip-the-line tickets",
                "Professional guide",
                "Telescope viewing"
            ),
            exclusions = listOf(
                "Transportation",
                "Meals",
                "Personal expenses"
            ),
            heroImageUrl = "",
            galleryUrls = listOf(),
            category = "Dubai"
        ),
        TravelPackage(
            id = "3",
            title = "Abu Dhabi City Tour",
            price = 350.0,
            currency = "AED",
            duration = "Full day",
            rating = 4.7f,
            reviewCount = 189,
            description = "Explore the capital of the UAE with visits to the magnificent Sheikh Zayed Grand Mosque, Emirates Palace, and the stunning Louvre Abu Dhabi.",
            highlights = listOf(
                "Sheikh Zayed Grand Mosque",
                "Emirates Palace photo stop",
                "Louvre Abu Dhabi",
                "Corniche drive",
                "Heritage Village"
            ),
            itinerary = listOf(
                ItineraryItem("08:00", "Hotel pickup from Dubai"),
                ItineraryItem("10:00", "Sheikh Zayed Grand Mosque"),
                ItineraryItem("12:00", "Emirates Palace photo stop"),
                ItineraryItem("13:00", "Lunch break"),
                ItineraryItem("14:30", "Louvre Abu Dhabi"),
                ItineraryItem("17:00", "Corniche drive"),
                ItineraryItem("18:30", "Return to Dubai")
            ),
            inclusions = listOf(
                "Air-conditioned transport",
                "Professional guide",
                "Museum entrance fees",
                "Bottled water"
            ),
            exclusions = listOf(
                "Lunch",
                "Personal expenses",
                "Tips"
            ),
            heroImageUrl = "",
            galleryUrls = listOf(),
            category = "Abu Dhabi"
        ),
        TravelPackage(
            id = "4",
            title = "Dubai Marina Cruise",
            price = 149.0,
            currency = "AED",
            duration = "2 hours",
            rating = 4.6f,
            reviewCount = 312,
            description = "Enjoy a luxurious dinner cruise along the stunning Dubai Marina, featuring international buffet dinner and entertainment.",
            highlights = listOf(
                "Luxury dhow cruise",
                "International buffet dinner",
                "Live entertainment",
                "Marina skyline views"
            ),
            itinerary = listOf(
                ItineraryItem("19:00", "Boarding"),
                ItineraryItem("19:30", "Cruise begins"),
                ItineraryItem("20:00", "Dinner service"),
                ItineraryItem("21:00", "Entertainment"),
                ItineraryItem("21:30", "Return to dock")
            ),
            inclusions = listOf(
                "Welcome drinks",
                "Buffet dinner",
                "Live entertainment",
                "Soft beverages"
            ),
            exclusions = listOf(
                "Transportation",
                "Alcoholic beverages",
                "Tips"
            ),
            heroImageUrl = "",
            galleryUrls = listOf(),
            category = "Dubai"
        ),
        TravelPackage(
            id = "5",
            title = "Musandam Dibba Day Trip",
            price = 450.0,
            currency = "AED",
            duration = "Full day",
            rating = 4.8f,
            reviewCount = 167,
            description = "Discover the stunning fjords of Oman with a full-day dhow cruise through the breathtaking Musandam Peninsula, including swimming, snorkeling, and dolphin watching.",
            highlights = listOf(
                "Dhow cruise through fjords",
                "Dolphin watching",
                "Swimming & snorkeling",
                "Buffet lunch onboard",
                "Stunning mountain scenery"
            ),
            itinerary = listOf(
                ItineraryItem("07:00", "Pickup from Dubai"),
                ItineraryItem("10:00", "Arrive at Dibba port"),
                ItineraryItem("10:30", "Board traditional dhow"),
                ItineraryItem("11:00", "Cruise through fjords"),
                ItineraryItem("12:30", "Swimming & snorkeling stop"),
                ItineraryItem("14:00", "Buffet lunch onboard"),
                ItineraryItem("15:30", "Return to port"),
                ItineraryItem("19:00", "Return to Dubai")
            ),
            inclusions = listOf(
                "Transportation from Dubai",
                "Dhow cruise",
                "Snorkeling equipment",
                "Buffet lunch",
                "Refreshments"
            ),
            exclusions = listOf(
                "Oman visa (if applicable)",
                "Personal expenses",
                "Tips"
            ),
            heroImageUrl = "",
            galleryUrls = listOf(),
            category = "Oman"
        )
    )
}
