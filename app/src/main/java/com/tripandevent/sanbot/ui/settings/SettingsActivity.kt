package com.tripandevent.sanbot.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.databinding.ActivitySettingsBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity

class SettingsActivity : BaseFullScreenActivity() {

    companion object {
        private const val CORRECT_PIN = "1234"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var isAuthenticated = false

    override val enableInactivityTimer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPasswordDialog()
    }

    private fun showPasswordDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_password, null)
        val pinInput = dialogView.findViewById<EditText>(R.id.pinInput)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_enter_pin))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.btn_unlock)) { _, _ ->
                val enteredPin = pinInput.text.toString()
                if (enteredPin == CORRECT_PIN) {
                    isAuthenticated = true
                    setupSettings()
                } else {
                    Toast.makeText(this, getString(R.string.error_wrong_pin), Toast.LENGTH_SHORT).show()
                    navigateBack()
                }
            }
            .setNegativeButton(getString(R.string.btn_cancel)) { _, _ ->
                navigateBack()
            }
            .setCancelable(false)
            .show()
    }

    private fun setupSettings() {
        binding.backButton.setOnClickListener { navigateBack() }

        binding.testConnectionButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_connection_success), Toast.LENGTH_SHORT).show()
        }

        binding.viewLogsButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_logs_coming_soon), Toast.LENGTH_SHORT).show()
        }

        binding.clearCacheButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.msg_cache_cleared), Toast.LENGTH_SHORT).show()
        }

        binding.logoutButton.setOnClickListener {
            navigateToWelcome()
        }
    }
}
