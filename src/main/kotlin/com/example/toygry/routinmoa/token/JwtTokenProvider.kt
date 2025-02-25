package com.example.toygry.routinmoa.token

import com.example.toygry.routinmoa.user.domain.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())

    // jwt 발급
    fun generateAccessToken(id: Long, email: String, name: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("id", id)
            .claim("name", name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    // refreshToken 발급
    fun generateRefreshToken(id: Long): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    // jwt 검증
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (exception: Exception) {
            false
        }
    }

    // token 정보 가져오기
    fun parseToken(token: String): TokenPayload {
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body

            val id = claims["id"]?.toString()?.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid token: Missing ID")
            val email = claims.subject ?: throw IllegalArgumentException("Invalid token: Missing email")
            val name = claims["name"]?.toString()
                ?: throw IllegalArgumentException("Invalid token: Missing name")

            return TokenPayload(id, email, name)

        } catch (ex: Exception) {
            throw IllegalArgumentException("Invalid JWT token: ${ex.message}")
        }
    }

}