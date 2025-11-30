package com.tripandevent.sanbot.ui.contact

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.ContactSubmission
import com.tripandevent.sanbot.databinding.ActivityThankYouBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity

class ThankYouActivity : BaseFullScreenActivity() {

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
        private const val AUTO_RETURN_DELAY = 30000L
    }

    private lateinit var binding: ActivityThankYouBinding
    private var contactSubmission: ContactSubmission? = null
    private val handler = Handler(Looper.getMainLooper())

    override val enableInactivityTimer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactSubmission = intent.getParcelableExtra(EXTRA_CONTACT)

        setupButtons()
        startAutoReturnTimer()
        playSuccessAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun setupButtons() {
        binding.smsButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_sms_sent), Toast.LENGTH_SHORT).show()
        }

        binding.whatsappButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_whatsapp_sent), Toast.LENGTH_SHORT).show()
        }

        binding.emailButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_email_sent), Toast.LENGTH_SHORT).show()
        }

        binding.backToHomeButton.setOnClickListener {
            navigateToWelcome()
        }
    }

    private fun startAutoReturnTimer() {
        handler.postDelayed({
            navigateToWelcome()
        }, AUTO_RETURN_DELAY)
    }

    private fun playSuccessAnimation() {
        binding.successIcon.alpha = 0f
        binding.successIcon.scaleX = 0.5f
        binding.successIcon.scaleY = 0.5f

        binding.successIcon.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .start()
    }
}
