package com.sanaigon.myboard.domain.dto

import com.sanaigon.myboard.domain.entity.Posts

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


