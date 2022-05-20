package com.sanaigon.myboard.api.dto

import com.sanaigon.myboard.domain.Posts

data class PostsResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String?
){
    companion object{
        fun of(posts: Posts): PostsResponseDto = PostsResponseDto(posts.id!!, posts.title, posts.content, posts.author)
    }
}


