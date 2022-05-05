package com.sanaigon.myboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing // JAP의 Audit기능을 사용하기 위해
@SpringBootApplication
class MyBoardApplication

fun main(args: Array<String>) {
    runApplication<MyBoardApplication>(*args)
}
