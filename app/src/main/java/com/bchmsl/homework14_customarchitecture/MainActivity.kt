package com.bchmsl.homework14_customarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bchmsl.homework14_customarchitecture.network.RetrofitProvider
import com.bchmsl.homework14_customarchitecture.utils.ResponseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        private val mFlow = MutableStateFlow<ResponseHandler>(ResponseHandler.Loading())
        val flow: StateFlow<ResponseHandler> get() = mFlow
        var currentPage = 1
        var openedItemIndex = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        when (currentPage) {
            1 -> openFirstPage()
            2 -> openSecondPage()
            else -> openFirstPage()
        }
    }

    fun openFirstPage() {
        val firstPage by lazy { FirstPage() }
        firstPage.openPage(this)
    }

    fun openSecondPage() {
        val secondPage by lazy { SecondPage() }
        secondPage.openPage(this)
    }

    suspend fun getItems() {
        lifecycleScope.launch {
            val response = RetrofitProvider.makeClient().getPosts()
            if (response.isSuccessful && response.body() != null) {
                mFlow.emit(ResponseHandler.Success(response.body()))
            } else {
                mFlow.emit(ResponseHandler.Error(response.errorBody().toString()))
            }
        }
    }

    override fun onBackPressed() {
        if (currentPage == 1) {
            super.onBackPressed()
        } else {
            currentPage -= 1
            init()
        }
    }
}