package com.example.sport.core.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.core.domain.model.Sport
import com.example.sport.R
import com.example.sport.databinding.ActivityDetailSportBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailSportActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private var TAG = "TESS"
    }

    private val detailSportViewModel: DetailSportViewModel by viewModel()

    private lateinit var binding: ActivityDetailSportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSportBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // setSupportActionBar(binding.toolbar)


        val detailSport = intent.getParcelableExtra<Sport>(EXTRA_DATA)
        showDetailSport(detailSport)
    }

    private fun showDetailSport(detailSport: Sport?) {
        detailSport?.let {
            supportActionBar?.title = detailSport.nameSport
            binding.content.tvDetailDescription.text = detailSport?.desc

            Glide.with(this)
                .load(detailSport.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailSport.favorite
            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailSportViewModel.setFavoriteSport(detailSport, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageResource(R.drawable.ic_red)
        } else {
            binding.fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}
