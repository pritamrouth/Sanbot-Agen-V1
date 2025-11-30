package com.tripandevent.sanbot.ui.contact

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.ContactSubmission
import com.tripandevent.sanbot.databinding.ActivityContactFormBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity

class ContactFormActivity : BaseFullScreenActivity() {

    companion object {
        const val EXTRA_PACKAGE_ID = "extra_package_id"
        const val EXTRA_PACKAGE_TITLE = "extra_package_title"
    }

    private lateinit var binding: ActivityContactFormBinding
    private var packageId: String? = null
    private var packageTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        packageId = intent.getStringExtra(EXTRA_PACKAGE_ID)
        packageTitle = intent.getStringExtra(EXTRA_PACKAGE_TITLE)

        setupToolbar()
        setupForm()
        setupValidation()
    }

    private fun setupToolbar() {
        binding.backButton.setOnClickListener { navigateBack() }
        binding.homeButton.setOnClickListener { navigateToWelcome() }
    }

    private fun setupForm() {
        packageTitle?.let { title ->
            binding.selectedPackageChip.text = title
            binding.selectedPackageChip.isChecked = true
        }

        binding.submitButton.setOnClickListener {
            if (validateForm()) {
                submitForm()
            }
        }
    }

    private fun setupValidation() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateSubmitButtonState()
            }
        }

        binding.nameInput.addTextChangedListener(textWatcher)
        binding.phoneInput.addTextChangedListener(textWatcher)
        binding.emailInput.addTextChangedListener(textWatcher)
    }

    private fun updateSubmitButtonState() {
        val nameValid = binding.nameInput.text.toString().length >= 2
        val phoneValid = binding.phoneInput.text.toString().length >= 7
        binding.submitButton.isEnabled = nameValid && phoneValid
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val name = binding.nameInput.text.toString()
        if (name.length < 2) {
            binding.nameInputLayout.error = getString(R.string.error_name_required)
            isValid = false
        } else {
            binding.nameInputLayout.error = null
        }

        val phone = binding.phoneInput.text.toString()
        if (phone.length < 7) {
            binding.phoneInputLayout.error = getString(R.string.error_phone_required)
            isValid = false
        } else {
            binding.phoneInputLayout.error = null
        }

        val email = binding.emailInput.text.toString()
        if (email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInputLayout.error = getString(R.string.error_email_invalid)
            isValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        return isValid
    }

    private fun submitForm() {
        val submission = ContactSubmission(
            name = binding.nameInput.text.toString(),
            phone = binding.phoneInput.text.toString(),
            countryCode = "+971",
            email = binding.emailInput.text.toString().takeIf { it.isNotEmpty() },
            selectedPackageIds = listOfNotNull(packageId),
            notes = binding.notesInput.text.toString().takeIf { it.isNotEmpty() }
        )

        binding.submitButton.isEnabled = false
        binding.submitButton.text = getString(R.string.submitting)

        binding.submitButton.postDelayed({
            val intent = Intent(this, ThankYouActivity::class.java)
            intent.putExtra(ThankYouActivity.EXTRA_CONTACT, submission)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 1500)
    }
}
