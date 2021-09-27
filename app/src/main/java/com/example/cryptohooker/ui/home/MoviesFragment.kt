package com.example.cryptohooker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.adapters.MoviesItemAdapter
import com.example.cryptohooker.base.BaseFragment
import com.example.cryptohooker.core.extensions.replaceFragment
import com.example.cryptohooker.core.extensions.showToastMsg
import com.example.cryptohooker.databinding.FragmentHomeBinding
import com.example.cryptohooker.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var moviesItemAdapter: MoviesItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        moviesItemAdapter = MoviesItemAdapter().also {
            it.onMovieItemSelectionListener { movieModel ->
                showToastMsg(movieModel.movieTitle)
            }
            it.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.productRecyclerView.adapter = it
        }

        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    progressDialog.show()
                }

                is ContentState -> {
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.recommendedMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            moviesItemAdapter.setItems(movies)
        }
    }
}
