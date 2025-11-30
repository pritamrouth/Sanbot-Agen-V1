package com.tripandevent.sanbot.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.tripandevent.sanbot.databinding.ActivityMainMenuBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity
import com.tripandevent.sanbot.ui.contact.ContactFormActivity
import com.tripandevent.sanbot.ui.media.MediaGalleryActivity
import com.tripandevent.sanbot.ui.packages.PackageListActivity
import com.tripandevent.sanbot.ui.settings.SettingsActivity
import com.tripandevent.sanbot.ui.voice.VoiceInteractionActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainMenuActivity : BaseFullScreenActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        startEntranceAnimations()
    }

    private fun setupClickListeners() {
        binding.cardTalkToMe.setOnClickListener {
            startActivity(Intent(this, VoiceInteractionActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.cardBrowsePackages.setOnClickListener {
            startActivity(Intent(this, PackageListActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.cardWatchVideos.setOnClickListener {
            startActivity(Intent(this, MediaGalleryActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.cardContactUs.setOnClickListener {
            startActivity(Intent(this, ContactFormActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun startEntranceAnimations() {
        val cards = listOf(
            binding.cardTalkToMe,
            binding.cardBrowsePackages,
            binding.cardWatchVideos,
            binding.cardContactUs
        )

        cards.forEach { card ->
            card.alpha = 0f
            card.translationX = 100f
        }

        binding.menuTitle.alpha = 0f
        binding.headerLogo.alpha = 0f

        lifecycleScope.launch {
            binding.headerLogo.animate()
                .alpha(1f)
                .setDuration(300)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()

            delay(150)

            binding.menuTitle.animate()
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()

            delay(200)

            cards.forEachIndexed { index, card ->
                delay(100L * index)
                card.animate()
                    .alpha(1f)
                    .translationX(0f)
                    .setDuration(400)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        navigateToWelcome()
    }
}
