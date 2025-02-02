package com.example.toygry.routinmoa.routine.adapter.out.persistence.entity

import com.example.toygry.routinmoa.routine.domain.entity.Routine
import com.example.toygry.routinmoa.user.adapter.out.persistence.entity.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "routines")
class RoutineEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity = UserEntity(),

    @Column
    var name: String = "",

    @Column
    var startDate: LocalDateTime = LocalDateTime.now(),

    @Column
    var endDate: LocalDateTime = LocalDateTime.now(),

    @Column
    var goalCount : Int = 0,

    @Column
    val completedCount: Int = 0,

    @Column
    var isDeleted: Boolean = false,

    @Column
    var isSuccess: Boolean = false,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now()

) {

    companion object {
        // ✅ 도메인 엔티티 → JPA 엔티티 변환
        fun fromDomain(routine: Routine, user: UserEntity): RoutineEntity {
            return RoutineEntity(
                id = routine.id ?: 0,  // ID가 없으면 기본값 0
                user = user,
                name = routine.name,
                startDate = routine.startDate,
                endDate = routine.endDate,
                goalCount = routine.goalCount,
                completedCount = routine.completedCount,
                isDeleted = routine.isDeleted,
                isSuccess = routine.isSuccess,
                createdAt = routine.createdAt,
                updatedAt = routine.updatedAt
            )
        }
    }

    fun toDomain(): Routine {
        return Routine(
            id = this.id,
            userId = this.user.id,
            name = this.name,
            startDate = this.startDate,
            endDate = this.endDate,
            goalCount = this.goalCount,
            completedCount = this.completedCount,
            isDeleted = this.isDeleted,
            isSuccess = this.isSuccess,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    fun delete() {
        this.isDeleted = true
        this.updatedAt = LocalDateTime.now()
    }
}





