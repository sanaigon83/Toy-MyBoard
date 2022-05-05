package com.sanaigon.myboard.api.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IndexControllerTest{

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun 메인페이지_로딩(){
        //when
        val body = restTemplate.getForObject("/", String::class.java)
        println(body)
        //then
        Assertions.assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }

}