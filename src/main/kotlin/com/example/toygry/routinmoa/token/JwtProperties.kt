package com.example.toygry.routinmoa.token

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    val secret = ""
    val accessTokenExpiration = 3600000
    val refreshTokenExpiration = 1209600000

}