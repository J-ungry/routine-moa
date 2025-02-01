package com.example.toygry.routinmoa.user.application.port.out

import com.example.toygry.routinmoa.user.domain.entity.User

interface UserPort {
    fun getUser(id : Long): User
}