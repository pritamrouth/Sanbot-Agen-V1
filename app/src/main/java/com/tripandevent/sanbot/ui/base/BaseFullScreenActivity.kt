package com.tripandevent.sanbot.ui.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.tripandevent.sanbot.ui.welcome.WelcomeActivity

abstract class BaseFullScreenActivity : AppCompatActivity() {

    private val inactivityHandler = Handler(Looper.getMainLooper())
    private val inactivityTimeout = 2 * 60 * 1000L // 2 minutes
    
    private val inactivityRunnable = Runnable {
        onInactivityTimeout()
    }
    
    protected open val enableInactivityTimer: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFullscreenMode()
    }

    override fun onResume() {
        super.onResume()
        if (enableInactivityTimer) {
            resetInactivityTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        stopInactivityTimer()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (enableInactivityTimer) {
            resetInactivityTimer()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setupFullscreenMode() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun resetInactivityTimer() {
        inactivityHandler.removeCallbacks(inactivityRunnable)
        inactivityHandler.postDelayed(inactivityRunnable, inactivityTimeout)
    }

    private fun stopInactivityTimer() {
        inactivityHandler.removeCallbacks(inactivityRunnable)
    }

    protected open fun onInactivityTimeout() {
        navigateToWelcome()
    }

    protected fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    protected fun navigateBack() {
        onBackPressedDispatcher.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
