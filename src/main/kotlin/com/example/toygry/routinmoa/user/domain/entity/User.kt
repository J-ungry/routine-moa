package com.example.toygry.routinmoa.user.domain.entity

import java.time.LocalDateTime

class User(
    val id: Long,
    val email: String,
    var name : String,
    val level : Int,
    val points: Int,
    val createdAt: LocalDateTime
)

