package com.example.cryptohooker.ui.newsfeed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.model.UserStoryModel
import com.example.cryptohooker.databinding.RowItemStoryBinding

class UserStoryAdapter :
    RecyclerView.Adapter<UserStoryAdapter.StoryViewHolder>() {

    private val storyItems: ArrayList<UserStoryModel> = arrayListOf()

    fun setItems(stories: List<UserStoryModel>) {
        storyItems.clear()
        storyItems.addAll(stories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = RowItemStoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(storyItems[position])
    }

    override fun getItemCount() = storyItems.size

    inner class StoryViewHolder(private val rowItemPopularMovieBinding: RowItemStoryBinding) :
        RecyclerView.ViewHolder(rowItemPopularMovieBinding.root) {

        fun bind(storyModel: UserStoryModel) {
            rowItemPopularMovieBinding.apply {
                itemImageStoryPicture.setImageResource(storyModel.image)
                itemTextStoryName.text = storyModel.userName
            }
        }
    }
}
