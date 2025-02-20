package com.example.toygry.routinmoa.user.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="email_verification")
class EmailVerificationEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String = "",

    @Column(nullable = false)
    val code: String = "",

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {}