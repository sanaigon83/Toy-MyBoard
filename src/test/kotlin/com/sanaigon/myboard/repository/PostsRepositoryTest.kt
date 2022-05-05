package com.sanaigon.myboard.repository

import com.sanaigon.myboard.domain.Posts
import org.aspectj.lang.annotation.After
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    lateinit var postRepository : PostRepository

    @After("test")
    fun cleanUp() {
        postRepository.deleteAll()
    }

    @Test
    fun 게시글저장_불러오기(){
        //given
        val title = "테스트 게시글"
        val content = "테스트 본문"

        postRepository.save(Posts(title, content, "sanaigon@gmail.com"))

        //when
        val postList: MutableList<Posts> = postRepository.findAll()

        //then
        val posts = postList.get(0)
        Assertions.assertThat(posts.title).isEqualTo(title)
        Assertions.assertThat(posts.content).isEqualTo(content)
    }
}