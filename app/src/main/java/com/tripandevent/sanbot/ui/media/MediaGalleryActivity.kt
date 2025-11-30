package com.tripandevent.sanbot.ui.media

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.MediaAsset
import com.tripandevent.sanbot.data.model.MediaType
import com.tripandevent.sanbot.data.repository.MediaRepository
import com.tripandevent.sanbot.databinding.ActivityMediaGalleryBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity

class MediaGalleryActivity : BaseFullScreenActivity() {

    private lateinit var binding: ActivityMediaGalleryBinding
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerViews()
        loadMedia()
    }

    private fun setupToolbar() {
        binding.backButton.setOnClickListener { navigateBack() }
        binding.homeButton.setOnClickListener { navigateToWelcome() }
    }

    private fun setupRecyclerViews() {
        videoAdapter = VideoAdapter { media ->
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra(VideoPlayerActivity.EXTRA_MEDIA, media)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.videosRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.videosRecyclerView.adapter = videoAdapter

        imageAdapter = ImageAdapter { media ->
        }
        binding.imagesRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.imagesRecyclerView.adapter = imageAdapter
    }

    private fun loadMedia() {
        val videos = MediaRepository.getVideos()
        val images = MediaRepository.getImages()
        
        videoAdapter.submitList(videos)
        imageAdapter.submitList(images)
    }

    inner class VideoAdapter(
        private val onItemClick: (MediaAsset) -> Unit
    ) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

        private var items: List<MediaAsset> = emptyList()

        fun submitList(list: List<MediaAsset>) {
            items = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val card: MaterialCardView = view.findViewById(R.id.videoCard)
            private val title: TextView = view.findViewById(R.id.videoTitle)
            private val duration: TextView = view.findViewById(R.id.videoDuration)

            fun bind(media: MediaAsset) {
                title.text = media.title
                duration.text = media.duration ?: ""
                card.setOnClickListener { onItemClick(media) }
            }
        }
    }

    inner class ImageAdapter(
        private val onItemClick: (MediaAsset) -> Unit
    ) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        private var items: List<MediaAsset> = emptyList()

        fun submitList(list: List<MediaAsset>) {
            items = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val card: MaterialCardView = view.findViewById(R.id.imageCard)

            fun bind(media: MediaAsset) {
                card.setOnClickListener { onItemClick(media) }
            }
        }
    }
}
