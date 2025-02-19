package com.example.toygry.routinmoa.user.adapter.out.persistence.repository

import com.example.toygry.routinmoa.user.adapter.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}