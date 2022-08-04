package com.bchmsl.homework14_customarchitecture.utils

import com.bchmsl.homework14_customarchitecture.model.PostsResponse

sealed class ResponseHandler(
    val isLoading: Boolean
) {
    class Success(val successData: PostsResponse?, isLoading: Boolean = false) :
        ResponseHandler(isLoading)

    class Error(val errorMessage: String?, isLoading: Boolean = false) :
        ResponseHandler(isLoading)

    class Loading(isLoading: Boolean = true) : ResponseHandler(isLoading)
}