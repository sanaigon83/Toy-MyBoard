package com.sanaigon.myboard.domain

import javax.persistence.*

@Entity
class Posts (
    @Column(length = 500, nullable = false)
    var title: String,
    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,
    var author: String?,
) : BaseTimeEntity() {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}

