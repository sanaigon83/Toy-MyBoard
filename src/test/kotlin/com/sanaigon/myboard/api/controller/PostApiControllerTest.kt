package com.sanaigon.myboard.api.controller

import com.sanaigon.myboard.api.dto.PostsSaveRequestDto
import com.sanaigon.myboard.api.dto.PostsUpdateRequestDto
import com.sanaigon.myboard.api.dto.toEntity
import com.sanaigon.myboard.domain.Posts
import com.sanaigon.myboard.repository.PostRepository
import org.aspectj.lang.annotation.After
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostApiControllerTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var postsRepository: PostRepository

    @AfterEach
    fun tearDown() {
        postsRepository.deleteAll()
    }

    @Test
    fun Posts_등록된다() {
        val title = "title"
        val content = "content"

        val requestDto: PostsSaveRequestDto = PostsSaveRequestDto(title, content, "sanaigon@gmail.com")

        //when
        postsRepository.save(requestDto.toEntity())

        //then

        val posts = postsRepository.findAll()[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }

    @Test
    fun Posts_업데이트된다() {

        val title = "title"
        val content = "content"
        val requestDto: PostsSaveRequestDto = PostsSaveRequestDto(title, content, "sanaigon@gmail.com")
        val id: Long = postsRepository.save(requestDto.toEntity()).id!!

        val newTitle = "newTitle"
        val newContent = "newContent"
        val requestUpdateDto = PostsUpdateRequestDto(newTitle, newContent)

        val url = "http://localhost:$port/api/v1/posts/$id"
        val requestEntity = HttpEntity<PostsUpdateRequestDto>(requestUpdateDto)

        //when
        val response: ResponseEntity<Long> = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        //then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isGreaterThan(0L)

        val posts = postsRepository.findAll()[0]
        assertThat(posts.title).isEqualTo(newTitle)
        assertThat(posts.content).isEqualTo(newContent)
    }

    @Test
    fun BaseTimeEntity_등록() {
        //given
        val now: LocalDateTime = LocalDateTime.now()

        val title = "title"
        val content = "content"
        val requestDto: PostsSaveRequestDto = PostsSaveRequestDto(title, content, "sanaigon@gmail.com")
        val id: Long = postsRepository.save(requestDto.toEntity()).id!!

        //when
        postsRepository.save(requestDto.toEntity())

        //then
        val posts: Posts = postsRepository.findAll()[0]
        assertThat(posts.createdDate!!).isAfter(now)
        assertThat(posts.modifiedDate!!).isAfter(now)
    }
}