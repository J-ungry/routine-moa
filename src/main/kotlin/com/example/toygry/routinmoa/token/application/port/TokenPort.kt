package com.example.toygry.routinmoa.token.application.port

import com.example.toygry.routinmoa.token.domain.TokenPayload
import org.springframework.http.HttpHeaders

interface TokenPort {
    fun generateToken(id: Long, email: String, name: String): HttpHeaders
    fun validateToken(token: String): Boolean
    fun parseToken(token: String): TokenPayload
}