package com.sanaigon.myboard.api.dto

import com.sanaigon.myboard.domain.Posts

data class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
)


fun PostsSaveRequestDto.toEntity(): Posts {
    return Posts(title, content, author)
}
