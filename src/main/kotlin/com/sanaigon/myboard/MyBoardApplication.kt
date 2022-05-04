package com.sanaigon.myboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyBoardApplication

fun main(args: Array<String>) {
    runApplication<MyBoardApplication>(*args)
}
