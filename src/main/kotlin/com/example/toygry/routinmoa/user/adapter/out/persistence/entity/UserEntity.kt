package com.example.toygry.routinmoa.user.adapter.out.persistence.entity

import com.example.toygry.routinmoa.user.domain.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users") // 테이블 이름 지정
class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    val id: Long = 0, // Primary Key

    @Column(nullable = false, unique = true) // 중복되지 않도록 unique 설정
    val email: String = "",

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var level: Int = 1, // 기본 레벨 1

    @Column(nullable = false)
    var points: Int = 0, // 초기 포인트 0

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

) {
    companion object {
        // ✅ 도메인 엔티티 → JPA 엔티티 변환
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id ?: 0,
                email = user.email,
                name = user.name,
                level = user.level,
                points = user.points,
                createdAt = user.createdAt
            )
        }
    }

    fun changeName(name: String) {
        this.name = name
    }
}