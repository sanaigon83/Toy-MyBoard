package com.sanaigon.myboard.service

import com.sanaigon.myboard.api.dto.*
import com.sanaigon.myboard.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(private val postRepository: PostRepository) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long {
        return postRepository.save(requestDto.toEntity()).id ?: throw java.util.NoSuchElementException("id not found")
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long =
        postRepository.findByIdOrNull(id)?.let {
            it.title = requestDto.title
            it.content = requestDto.content
            it.id
        } ?: throw java.util.NoSuchElementException("NoSuch $id")


    fun findById(id: Long): PostsResponseDto =
        postRepository.findByIdOrNull(id)?.let {
            PostsResponseDto(it.id!!, it.title, it.content, it.author)
        } ?: throw java.util.NoSuchElementException("NoSuchelements $id")

    @Transactional(readOnly = true)
    fun findAllDesc(): List<PostsListsResponseDto> =
        postRepository.findAllDesc()?.map { PostsListsResponseDto.of(it) } ?: listOf()
        //postRepository.findAllDesc()?.map { PostsListsResponseDto.of(it)}
}

