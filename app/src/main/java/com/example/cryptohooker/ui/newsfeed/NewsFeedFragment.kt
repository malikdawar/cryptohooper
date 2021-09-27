package com.example.cryptohooker.ui.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptohooker.base.BaseFragment
import com.example.cryptohooker.core.extensions.showToastMsg
import com.example.cryptohooker.databinding.FragmentNewsFeedBinding
import com.example.cryptohooker.ui.newsfeed.adapters.NewsFeedAdapter
import com.example.cryptohooker.ui.newsfeed.adapters.UserStoryAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * The NewsFeedFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class NewsFeedFragment : BaseFragment() {

    private val viewModel: NewsFeedViewModel by viewModels()
    private lateinit var binding: FragmentNewsFeedBinding
    private val userStoryAdapter: UserStoryAdapter = UserStoryAdapter()
    private val newsFeedAdapter: NewsFeedAdapter = NewsFeedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserStories()
        binding.recyclerViewUserStories.adapter = userStoryAdapter
        binding.recyclerViewNewsFeed.adapter = newsFeedAdapter

        initObservations()
    }

    private fun initObservations() {
        viewModel.userStoryLiveListModel.observe(viewLifecycleOwner) { stories ->
            userStoryAdapter.setItems(stories)
        }

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

        viewModel.newsFeedModelLiveData.observe(viewLifecycleOwner) {
            newsFeedAdapter.setItems(it)
        }
    }
}
