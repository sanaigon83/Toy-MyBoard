package com.sanaigon.myboard.api.controller

import com.sanaigon.myboard.domain.dto.PostsResponseDto
import com.sanaigon.myboard.domain.dto.PostsSaveRequestDto
import com.sanaigon.myboard.domain.dto.PostsUpdateRequestDto
import com.sanaigon.myboard.service.PostsService
import org.springframework.web.bind.annotation.*

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