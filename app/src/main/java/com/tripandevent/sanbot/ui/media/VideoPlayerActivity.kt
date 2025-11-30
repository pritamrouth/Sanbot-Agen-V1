package com.tripandevent.sanbot.ui.media

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.tripandevent.sanbot.data.model.MediaAsset
import com.tripandevent.sanbot.databinding.ActivityVideoPlayerBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity

class VideoPlayerActivity : BaseFullScreenActivity() {

    companion object {
        const val EXTRA_MEDIA = "extra_media"
    }

    private lateinit var binding: ActivityVideoPlayerBinding
    private var exoPlayer: ExoPlayer? = null
    private var mediaAsset: MediaAsset? = null

    override val enableInactivityTimer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaAsset = intent.getParcelableExtra(EXTRA_MEDIA)

        setupPlayerControls()
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun setupPlayerControls() {
        binding.closeButton.setOnClickListener {
            navigateBack()
        }

        binding.playerView.setOnClickListener {
            toggleControlsVisibility()
        }
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build().apply {
            binding.playerView.player = this

            val videoUri = try {
                val rawId = resources.getIdentifier("sample_video", "raw", packageName)
                if (rawId != 0) {
                    Uri.parse("android.resource://$packageName/$rawId")
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }

            videoUri?.let { uri ->
                val mediaItem = MediaItem.fromUri(uri)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }

            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_ENDED) {
                        navigateBack()
                    }
                }
            })
        }
    }

    private fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    private fun toggleControlsVisibility() {
        if (binding.closeButton.visibility == View.VISIBLE) {
            binding.closeButton.visibility = View.GONE
        } else {
            binding.closeButton.visibility = View.VISIBLE
            binding.closeButton.postDelayed({
                binding.closeButton.visibility = View.GONE
            }, 3000)
        }
    }
}
