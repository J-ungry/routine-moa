package com.example.toygry.routinmoa.user.application.port.`in`

import com.example.toygry.routinmoa.user.domain.entity.User

interface UserUseCase {

    fun getUser(id: Long): User
}