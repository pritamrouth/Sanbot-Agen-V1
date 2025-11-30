package com.tripandevent.sanbot.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.databinding.ActivityWelcomeBinding
import com.tripandevent.sanbot.ui.menu.MainMenuActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
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
        binding.rootLayout.setOnClickListener {
            navigateToMainMenu()
        }

        binding.startButton.setOnClickListener {
            navigateToMainMenu()
        }
    }

    private fun startEntranceAnimations() {
        lifecycleScope.launch {
            animateLogo()
            delay(300)
            animateTitle()
            delay(200)
            animateSubtitle()
            delay(200)
            animateButton()
            startButtonPulseAnimation()
        }
    }

    private fun animateLogo() {
        binding.logoImage.animate()
            .alpha(1f)
            .setDuration(500)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }

    private fun animateTitle() {
        binding.welcomeTitle.apply {
            translationY = 50f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    private fun animateSubtitle() {
        binding.welcomeSubtitle.apply {
            translationY = 50f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    private fun animateButton() {
        binding.startButton.apply {
            scaleX = 0.8f
            scaleY = 0.8f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    private fun startButtonPulseAnimation() {
        val scaleUpX = ObjectAnimator.ofFloat(binding.startButton, View.SCALE_X, 1f, 1.05f)
        val scaleUpY = ObjectAnimator.ofFloat(binding.startButton, View.SCALE_Y, 1f, 1.05f)
        val scaleDownX = ObjectAnimator.ofFloat(binding.startButton, View.SCALE_X, 1.05f, 1f)
        val scaleDownY = ObjectAnimator.ofFloat(binding.startButton, View.SCALE_Y, 1.05f, 1f)

        scaleUpX.duration = 1000
        scaleUpY.duration = 1000
        scaleDownX.duration = 1000
        scaleDownY.duration = 1000

        val pulseUp = AnimatorSet().apply {
            playTogether(scaleUpX, scaleUpY)
        }

        val pulseDown = AnimatorSet().apply {
            playTogether(scaleDownX, scaleDownY)
        }

        val pulse = AnimatorSet().apply {
            playSequentially(pulseUp, pulseDown)
            addListener(object : android.animation.Animator.AnimatorListener {
                override fun onAnimationStart(animation: android.animation.Animator) {}
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    start()
                }
                override fun onAnimationCancel(animation: android.animation.Animator) {}
                override fun onAnimationRepeat(animation: android.animation.Animator) {}
            })
        }
        pulse.start()
    }

    private fun navigateToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
