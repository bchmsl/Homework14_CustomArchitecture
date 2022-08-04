package com.bchmsl.homework14_customarchitecture

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bchmsl.homework14_customarchitecture.adapters.PostsAdapter
import com.bchmsl.homework14_customarchitecture.databinding.PagePostsBinding
import com.bchmsl.homework14_customarchitecture.utils.ResponseHandler
import kotlinx.coroutines.launch

class FirstPage : AppCompatPage<MainActivity>() {

    override fun openPage(activity: AppCompatActivity) {
        val currentActivity by lazy { activity as MainActivity }
        MainActivity.currentPage = 1
        val binding = PagePostsBinding.inflate(currentActivity.layoutInflater)
        val adapter by lazy { PostsAdapter() }

        // The best part of code <3
        currentActivity.setContentView(binding.root)

        currentActivity.lifecycleScope.launch {
            currentActivity.getItems()
            MainActivity.flow.collect {
                binding.progressBar.isVisible = it.isLoading
                when (it) {
                    is ResponseHandler.Success -> {
                        binding.rvPosts.adapter = adapter
                        adapter.submitList(it.successData?.content)
                    }
                    is ResponseHandler.Error -> Toast.makeText(
                        currentActivity,
                        it.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {}
                }
            }
        }
        adapter.itemClick = {
            MainActivity.openedItemIndex = it
            currentActivity.openSecondPage()
        }
        binding.swipeRefresh.setOnRefreshListener {
            currentActivity.openFirstPage()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}