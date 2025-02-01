package com.example.toygry.routinmoa.user.application.service

import com.example.toygry.routinmoa.user.adapter.`in`.UserController
import com.example.toygry.routinmoa.user.adapter.`in`.UserResponse
import com.example.toygry.routinmoa.user.application.port.`in`.UserUseCase
import com.example.toygry.routinmoa.user.application.port.out.UserPort
import com.example.toygry.routinmoa.user.domain.entity.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    val userPort: UserPort
):UserUseCase {
    override fun getUser(id: Long): User {
        return userPort.getUser(id)
    }
}