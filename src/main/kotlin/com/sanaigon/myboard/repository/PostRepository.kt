package com.sanaigon.myboard.repository

import com.sanaigon.myboard.domain.Posts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    fun findAllDesc() : List<Posts>?
}