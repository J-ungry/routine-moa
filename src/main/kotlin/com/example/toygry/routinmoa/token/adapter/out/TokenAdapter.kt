package com.example.toygry.routinmoa.token.adapter.out

import com.example.toygry.routinmoa.token.infrastructure.JwtTokenProvider
import com.example.toygry.routinmoa.token.application.port.TokenPort
import com.example.toygry.routinmoa.token.domain.TokenPayload
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class TokenAdapter(
    private val jwtTokenProvider: JwtTokenProvider
): TokenPort {
    override fun generateToken(id: Long, email: String, name: String): HttpHeaders {
        return jwtTokenProvider.generateToken(id, email, name)
    }

    override fun validateToken(token: String): Boolean {
        return jwtTokenProvider.validateToken(token)
    }

    override fun parseToken(token: String): TokenPayload {
        return jwtTokenProvider.parseToken(token)
    }
}