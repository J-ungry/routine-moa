package com.example.toygry.routinmoa.user.domain.vo

import org.springframework.http.HttpHeaders

data class SignResult(
    val status: Boolean,
    val message: String,
    val token: HttpHeaders? = null
)