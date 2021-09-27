package com.example.cryptohooker.ui.newsfeed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptohooker.core.utils.load
import com.example.cryptohooker.data.model.NewsFeedModel
import com.example.cryptohooker.databinding.RowItemNewsFeedBinding

class NewsFeedAdapter :
    RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {

    private val newsFeedItems: ArrayList<NewsFeedModel> = arrayListOf()

    fun setItems(newsFeeds: List<NewsFeedModel>) {
        newsFeedItems.clear()
        newsFeedItems.addAll(newsFeeds)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = RowItemNewsFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        holder.bind(newsFeedItems[position])
    }

    override fun getItemCount() = newsFeedItems.size

    inner class NewsFeedViewHolder(private val rowItemNewsFeedBinding: RowItemNewsFeedBinding) :
        RecyclerView.ViewHolder(rowItemNewsFeedBinding.root) {

        fun bind(newsFeedModel: NewsFeedModel) {
            rowItemNewsFeedBinding.apply {
                itemNewsUserImage.load(newsFeedModel.userImageUrl)
                newsFeedModel.postImage?.let { postImg->
                    itemNewsImagePost.load(postImg)
                }
                itemNewsTextUserName.text = newsFeedModel.userFullName
                itemNewsTextPostDate.text = newsFeedModel.createdAt
                itemNewsTextPostDescription.text = newsFeedModel.postMessage
                itemTextNewsLikes.text = newsFeedModel.likeCount.toString()
                itemTextNewsComments.text = newsFeedModel.commentCount.toString()
            }
        }
    }
}
