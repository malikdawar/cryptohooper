package com.example.cryptohooker.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.base.BaseFragment
import com.example.cryptohooker.core.extensions.gone
import com.example.cryptohooker.core.extensions.showToastMsg
import com.example.cryptohooker.core.extensions.visible
import com.example.cryptohooker.databinding.FragmentMoviesBinding
import com.example.cryptohooker.ui.movies.adapters.PopularMoviesAdapter
import com.example.cryptohooker.ui.movies.adapters.RecommendedMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var recommendedRecommendedMoviesAdapter: RecommendedMoviesAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recommendedRecommendedMoviesAdapter = RecommendedMoviesAdapter().also {
            it.onMovieItemSelectionListener { movieModel ->
                showToastMsg(movieModel.movieTitle)
            }
            it.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.rvRecommendedMovies.adapter = it
        }

        popularMoviesAdapter = PopularMoviesAdapter().also {
            binding.rvPopularMovies.adapter = it
        }

        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    binding.layoutPopularMoviesControl.gone()
                    binding.layoutRecommendedMoviesControl.gone()
                    progressDialog.show()
                }

                is ContentState -> {
                    binding.layoutPopularMoviesControl.visible()
                    binding.layoutRecommendedMoviesControl.visible()
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.recommendedMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            recommendedRecommendedMoviesAdapter.setItems(movies)
        }

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            popularMoviesAdapter.setItems(movies)
        }
    }
}
