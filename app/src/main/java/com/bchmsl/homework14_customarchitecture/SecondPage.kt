package com.bchmsl.homework14_customarchitecture

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bchmsl.homework14_customarchitecture.databinding.PagePostOpenedBinding
import com.bchmsl.homework14_customarchitecture.extensions.setImage
import com.bchmsl.homework14_customarchitecture.extensions.toDate
import com.bchmsl.homework14_customarchitecture.model.PostsResponse
import com.bchmsl.homework14_customarchitecture.utils.ResponseHandler
import kotlinx.coroutines.launch

class SecondPage : AppCompatPage<MainActivity>(){
    override fun openPage(activity : AppCompatActivity){
        val currentActivity by lazy { activity as MainActivity }
        MainActivity.currentPage = 2
        val binding = PagePostOpenedBinding.inflate(currentActivity.layoutInflater)

        // The best part of code <3
        currentActivity.setContentView(binding.root)

        var openedPost: PostsResponse.Post? = null

        currentActivity.lifecycleScope.launch {
            MainActivity.flow.collect {
                when (it) {
                    is ResponseHandler.Success -> {
                        openedPost = it.successData?.content?.get(MainActivity.openedItemIndex)
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
        if (openedPost != null) {
            binding.apply {
                tvTitle.text = openedPost!!.titleKA
                tvDate.text = openedPost!!.publishDate.toDate()
                tvDescription.text = openedPost!!.descriptionKA
                openedPost!!.cover?.let { ivCover.setImage(it) }
                btnBack.setOnClickListener {
                    currentActivity.openFirstPage()
                }
            }
        }
    }
}
