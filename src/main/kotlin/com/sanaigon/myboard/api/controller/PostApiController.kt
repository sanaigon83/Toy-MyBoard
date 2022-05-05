package com.sanaigon.myboard.api.controller

import com.sanaigon.myboard.api.dto.PostsResponseDto
import com.sanaigon.myboard.api.dto.PostsSaveRequestDto
import com.sanaigon.myboard.api.dto.PostsUpdateRequestDto
import com.sanaigon.myboard.service.PostsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class PostApiController(val postsService: PostsService) {

    @PostMapping("/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long {
        return postsService.save(requestDto)
    }

    @PutMapping("/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostsUpdateRequestDto): Long {
        return postsService.update(id, requestDto)
    }

    @GetMapping("/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto = postsService.findById(id)
}