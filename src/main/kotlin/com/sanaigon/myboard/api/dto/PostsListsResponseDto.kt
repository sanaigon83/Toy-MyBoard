package com.sanaigon.myboard.api.dto

import com.sanaigon.myboard.domain.Posts
import java.time.LocalDateTime

data class PostsListsResponseDto(
    val id: Long,
    val title: String,
    val author: String?,
    val modifiedDate: LocalDateTime?
) {
    companion object {
        fun of(posts: Posts): PostsListsResponseDto =
            PostsListsResponseDto(
                posts.id!!,
                posts.title,
                posts.author,
                posts.modifiedDate
            )

    }
}
