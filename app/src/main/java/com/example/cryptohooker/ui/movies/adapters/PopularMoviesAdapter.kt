package com.example.cryptohooker.ui.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.core.utils.load
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.databinding.RowItemPopularMovieBinding

class PopularMoviesAdapter :
    RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder>() {

    private val movieItems: ArrayList<MovieModel> = arrayListOf()

    fun setItems(movies: List<MovieModel>) {
        movieItems.clear()
        movieItems.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowItemPopularMovieBinding.inflate(
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

    inner class MovieViewHolder(private val rowItemPopularMovieBinding: RowItemPopularMovieBinding) :
        RecyclerView.ViewHolder(rowItemPopularMovieBinding.root) {

        fun bind(movieModel: MovieModel) {
            rowItemPopularMovieBinding.apply {
                itemImgViewMoviePoster.load(movieModel.movieImage)
                itemTextMovieTitle.text = movieModel.movieTitle
                itemTextMovieTimeDetails.text = "${movieModel.movieYear}"
            }
        }
    }
}
