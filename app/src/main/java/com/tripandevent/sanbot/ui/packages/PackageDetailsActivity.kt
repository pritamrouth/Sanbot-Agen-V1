package com.tripandevent.sanbot.ui.packages

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.TravelPackage
import com.tripandevent.sanbot.databinding.ActivityPackageDetailsBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity
import com.tripandevent.sanbot.ui.contact.ContactFormActivity

class PackageDetailsActivity : BaseFullScreenActivity() {

    companion object {
        const val EXTRA_PACKAGE = "extra_package"
    }

    private lateinit var binding: ActivityPackageDetailsBinding
    private var travelPackage: TravelPackage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPackageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        travelPackage = intent.getParcelableExtra(EXTRA_PACKAGE)
        
        setupToolbar()
        setupPackageDetails()
        setupActionButtons()
        setupExpandableSections()
    }

    private fun setupToolbar() {
        binding.backButton.setOnClickListener { navigateBack() }
        binding.homeButton.setOnClickListener { navigateToWelcome() }
    }

    private fun setupPackageDetails() {
        travelPackage?.let { pkg ->
            binding.packageTitle.text = pkg.title
            binding.packagePrice.text = "${pkg.currency} ${pkg.price.toInt()} per person"
            binding.packageRating.text = "⭐ ${pkg.rating} (${pkg.reviewCount} reviews)"
            binding.packageDescription.text = pkg.description

            binding.highlightsContent.removeAllViews()
            pkg.highlights.forEach { highlight ->
                val textView = TextView(this).apply {
                    text = "• $highlight"
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.dark_gray, null))
                    setPadding(0, 4, 0, 4)
                }
                binding.highlightsContent.addView(textView)
            }

            binding.itineraryContent.removeAllViews()
            pkg.itinerary.forEach { item ->
                val textView = TextView(this).apply {
                    text = "${item.time} - ${item.activity}"
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.dark_gray, null))
                    setPadding(0, 4, 0, 4)
                }
                binding.itineraryContent.addView(textView)
            }

            binding.inclusionsContent.removeAllViews()
            pkg.inclusions.forEach { inclusion ->
                val textView = TextView(this).apply {
                    text = "✓ $inclusion"
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.success_green, null))
                    setPadding(0, 4, 0, 4)
                }
                binding.inclusionsContent.addView(textView)
            }

            binding.exclusionsContent.removeAllViews()
            pkg.exclusions.forEach { exclusion ->
                val textView = TextView(this).apply {
                    text = "✗ $exclusion"
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.error_red, null))
                    setPadding(0, 4, 0, 4)
                }
                binding.exclusionsContent.addView(textView)
            }
        }
    }

    private fun setupActionButtons() {
        binding.bookNowButton.setOnClickListener {
            val intent = Intent(this, ContactFormActivity::class.java)
            travelPackage?.let { pkg ->
                intent.putExtra(ContactFormActivity.EXTRA_PACKAGE_ID, pkg.id)
                intent.putExtra(ContactFormActivity.EXTRA_PACKAGE_TITLE, pkg.title)
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.getQuoteButton.setOnClickListener {
            val intent = Intent(this, ContactFormActivity::class.java)
            travelPackage?.let { pkg ->
                intent.putExtra(ContactFormActivity.EXTRA_PACKAGE_ID, pkg.id)
                intent.putExtra(ContactFormActivity.EXTRA_PACKAGE_TITLE, pkg.title)
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun setupExpandableSections() {
        binding.highlightsHeader.setOnClickListener {
            toggleSection(binding.highlightsContent, binding.highlightsArrow)
        }
        binding.itineraryHeader.setOnClickListener {
            toggleSection(binding.itineraryContent, binding.itineraryArrow)
        }
        binding.inclusionsHeader.setOnClickListener {
            toggleSection(binding.inclusionsContent, binding.inclusionsArrow)
        }
        binding.exclusionsHeader.setOnClickListener {
            toggleSection(binding.exclusionsContent, binding.exclusionsArrow)
        }
    }

    private fun toggleSection(content: View, arrow: View) {
        if (content.visibility == View.VISIBLE) {
            content.visibility = View.GONE
            arrow.rotation = 0f
        } else {
            content.visibility = View.VISIBLE
            arrow.rotation = 180f
        }
    }
}
