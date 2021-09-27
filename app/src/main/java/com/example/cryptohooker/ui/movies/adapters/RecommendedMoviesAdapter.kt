package com.example.cryptohooker.ui.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.core.utils.load
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.databinding.RowItemRecommendedMoviesBinding

class RecommendedMoviesAdapter :
    RecyclerView.Adapter<RecommendedMoviesAdapter.MovieViewHolder>() {

    lateinit var onMovieItemSelected: (MovieModel) -> Unit
    private val movieItems: ArrayList<MovieModel> = arrayListOf()

    fun setItems(movies: List<MovieModel>) {
        movieItems.clear()
        movieItems.addAll(movies)
        notifyDataSetChanged()
    }

    fun onMovieItemSelectionListener(listener: (MovieModel) -> Unit) {
        onMovieItemSelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowItemRecommendedMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    override fun getItemCount() = movieItems.size

    inner class MovieViewHolder(private val rowItemRecommendedMoviesBinding: RowItemRecommendedMoviesBinding) :
        RecyclerView.ViewHolder(rowItemRecommendedMoviesBinding.root) {

        fun bind(movieModel: MovieModel) {
            rowItemRecommendedMoviesBinding.apply {
                itemImgViewMoviePoster.load(movieModel.movieImage)
                itemTextMovieTitle.text = movieModel.movieTitle
                itemTextMovieTimeDetails.text = "${movieModel.movieYear}"
                itemTextMovieCategory.text = movieModel.movieGenre
            }
        }
    }
}
