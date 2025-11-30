package com.tripandevent.sanbot.ui.packages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.tripandevent.sanbot.R
import com.tripandevent.sanbot.data.model.TravelPackage
import com.tripandevent.sanbot.data.repository.PackageRepository
import com.tripandevent.sanbot.databinding.ActivityPackageListBinding
import com.tripandevent.sanbot.ui.base.BaseFullScreenActivity
import com.tripandevent.sanbot.ui.menu.MainMenuActivity

class PackageListActivity : BaseFullScreenActivity() {

    private lateinit var binding: ActivityPackageListBinding
    private lateinit var adapter: PackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPackageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadPackages()
    }

    private fun setupToolbar() {
        binding.backButton.setOnClickListener { navigateBack() }
        binding.homeButton.setOnClickListener { navigateToWelcome() }
    }

    private fun setupRecyclerView() {
        adapter = PackageAdapter { pkg ->
            val intent = Intent(this, PackageDetailsActivity::class.java)
            intent.putExtra(PackageDetailsActivity.EXTRA_PACKAGE, pkg)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.packagesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.packagesRecyclerView.adapter = adapter
    }

    private fun loadPackages() {
        val packages = PackageRepository.getAllPackages()
        adapter.submitList(packages)
    }

    inner class PackageAdapter(
        private val onItemClick: (TravelPackage) -> Unit
    ) : RecyclerView.Adapter<PackageAdapter.ViewHolder>() {

        private var items: List<TravelPackage> = emptyList()

        fun submitList(list: List<TravelPackage>) {
            items = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_package_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val card: MaterialCardView = view.findViewById(R.id.packageCard)
            private val title: TextView = view.findViewById(R.id.packageTitle)
            private val priceAndDuration: TextView = view.findViewById(R.id.packagePriceAndDuration)
            private val rating: TextView = view.findViewById(R.id.packageRating)

            fun bind(pkg: TravelPackage) {
                title.text = pkg.title
                priceAndDuration.text = "${pkg.currency} ${pkg.price.toInt()} • ${pkg.duration}"
                rating.text = "⭐ ${pkg.rating} (${pkg.reviewCount} reviews)"
                card.setOnClickListener { onItemClick(pkg) }
            }
        }
    }
}
