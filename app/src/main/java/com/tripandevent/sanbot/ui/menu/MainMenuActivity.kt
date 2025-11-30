package com.tripandevent.sanbot.ui.menu

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.databinding.ActivityMainMenuBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFullscreenMode()
        setupClickListeners()
        startEntranceAnimations()
    }

    private fun setupFullscreenMode() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setupClickListeners() {
        binding.cardTours.setOnClickListener {
            showToast("Tours - Coming Soon")
        }

        binding.cardEvents.setOnClickListener {
            showToast("Events - Coming Soon")
        }

        binding.cardInfo.setOnClickListener {
            showToast("Information - Coming Soon")
        }

        binding.cardHelp.setOnClickListener {
            showToast("Help - Coming Soon")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun startEntranceAnimations() {
        val cards = listOf(
            binding.cardTours,
            binding.cardEvents,
            binding.cardInfo,
            binding.cardHelp
        )

        cards.forEachIndexed { index, card ->
            card.alpha = 0f
            card.translationY = 100f
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
                    .translationY(0f)
                    .setDuration(400)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
