package com.example.sport.core.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.source.local.Resource
import com.example.core.ui.SportAdapter
import com.example.sport.R
import com.example.sport.core.MyApplication
import com.example.sport.core.detail.DetailSportActivity
import com.example.sport.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSportActivity::class.java)
                intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }


            homeViewModel.sport.observe(viewLifecycleOwner, { sport ->
                if (sport != null) {
                    when (sport) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            sportAdapter.setData(sport.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewErrorData.root.visibility = View.VISIBLE
                            binding.viewErrorData.tvError.text = sport.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
