package com.tripandevent.sanbot.ui.voice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.ConversationMessage
import com.tripandevent.sanbot.data.model.MessageRole
import com.tripandevent.sanbot.databinding.ActivityVoiceInteractionBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity
import java.util.UUID

class VoiceInteractionActivity : BaseFullScreenActivity() {

    private lateinit var binding: ActivityVoiceInteractionBinding
    private lateinit var adapter: ConversationAdapter
    private val conversations = mutableListOf<ConversationMessage>()

    private var isListening = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVoiceInteractionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupMicrophoneButton()
        updateUIState()
    }

    private fun setupToolbar() {
        binding.backButton.setOnClickListener { navigateBack() }
        binding.homeButton.setOnClickListener { navigateToWelcome() }
    }

    private fun setupRecyclerView() {
        adapter = ConversationAdapter()
        binding.conversationRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.conversationRecyclerView.adapter = adapter
    }

    private fun setupMicrophoneButton() {
        binding.microphoneButton.setOnClickListener {
            if (isListening) {
                stopListening()
            } else {
                startListening()
            }
        }
    }

    private fun startListening() {
        isListening = true
        updateUIState()
        
        binding.microphoneButton.postDelayed({
            simulateVoiceInput()
        }, 3000)
    }

    private fun stopListening() {
        isListening = false
        updateUIState()
    }

    private fun simulateVoiceInput() {
        stopListening()
        
        binding.statusText.text = getString(R.string.status_processing)
        
        val userMessage = ConversationMessage(
            id = UUID.randomUUID().toString(),
            role = MessageRole.USER,
            text = "Show me Dubai packages"
        )
        conversations.add(userMessage)
        adapter.submitList(conversations.toList())
        scrollToBottom()

        binding.microphoneButton.postDelayed({
            binding.statusText.text = getString(R.string.status_speaking)
            
            val agentMessage = ConversationMessage(
                id = UUID.randomUUID().toString(),
                role = MessageRole.AGENT,
                text = "Here are our top Dubai packages! We have the Dubai Desert Safari for AED 299, the Burj Khalifa Tour for AED 199, and the Dubai Marina Cruise for AED 149. Would you like more details about any of these?"
            )
            conversations.add(agentMessage)
            adapter.submitList(conversations.toList())
            scrollToBottom()

            binding.microphoneButton.postDelayed({
                updateUIState()
            }, 2000)
        }, 1500)
    }

    private fun scrollToBottom() {
        binding.conversationRecyclerView.smoothScrollToPosition(conversations.size - 1)
    }

    private fun updateUIState() {
        if (isListening) {
            binding.statusText.text = getString(R.string.status_listening)
            binding.microphoneButton.setBackgroundResource(R.drawable.bg_mic_button_active)
            binding.waveformView.visibility = View.VISIBLE
            binding.instructionText.text = getString(R.string.instruction_tap_stop)
        } else {
            binding.statusText.text = getString(R.string.status_idle)
            binding.microphoneButton.setBackgroundResource(R.drawable.bg_mic_button)
            binding.waveformView.visibility = View.GONE
            binding.instructionText.text = getString(R.string.instruction_tap_speak)
        }
    }

    inner class ConversationAdapter : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {

        private var items: List<ConversationMessage> = emptyList()

        fun submitList(list: List<ConversationMessage>) {
            items = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutId = if (viewType == 0) R.layout.item_message_user else R.layout.item_message_agent
            val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        override fun getItemViewType(position: Int): Int {
            return if (items[position].role == MessageRole.USER) 0 else 1
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val messageText: TextView = view.findViewById(R.id.messageText)

            fun bind(message: ConversationMessage) {
                messageText.text = message.text
            }
        }
    }
}
