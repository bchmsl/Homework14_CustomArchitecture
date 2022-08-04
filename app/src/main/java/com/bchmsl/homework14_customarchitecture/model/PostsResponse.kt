package com.bchmsl.homework14_customarchitecture.model

import com.squareup.moshi.Json
import java.io.Serializable

data class PostsResponse(
    val content: List<Post>
) {
    data class Post(
        val id: String?,
        val descriptionKA: String?,
        val titleKA: String?,
        @field:Json(name = "publish_date")
        val publishDate: String?,
        val cover: String?,
    ):Serializable
}