package com.example.sport.favorite.di

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.SportAdapter
import com.example.sport.core.detail.DetailSportActivity
import com.example.sport.favorite.R
import com.example.sport.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private val favoriteViewModel : FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(this, DetailSportActivity::class.java)
                intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteSport.observe(this, { dataSport ->
                sportAdapter.setData(dataSport)
                binding.viewEmptydata.root.visibility = if (dataSport.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sportAdapter
            }


    }
}