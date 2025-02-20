package com.example.toygry.routinmoa.user.adapter.out.persistence.repository

import com.example.toygry.routinmoa.user.adapter.out.persistence.entity.EmailVerificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmailVerificationRepository: JpaRepository<EmailVerificationEntity, Long> {
    fun deleteByEmail(email: String)
}