package com.example.toygry.routinmoa.routine.domain.entity

import com.example.toygry.routinmoa.routine.adapter.out.persistence.entity.RoutineEntity
import com.example.toygry.routinmoa.user.adapter.out.persistence.entity.UserEntity
import java.time.LocalDateTime

class Routine(
    val id: Long,
    val userId: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val goalCount: Int,
    val completedCount: Int,
    val isDeleted: Boolean,
    val isSuccess: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
    ) {

    companion object {
        fun create(
            userId: Long,
            name: String,
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            goalCount: Int
        ): Routine {
            return Routine(
                0,
                userId,
                name,
                startDate,
                endDate,
                goalCount,
                0,
                false,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        }
    }
}

//현재 Routine의 create() 메서드는 객체가 생성된 이후에만 호출할 수 있는 인스턴스 메서드입니다.
//그러나 루틴을 생성할 때는 **객체 없이 생성하는 정적 메서드(팩토리 메서드)**가 필요합니다.
//
// => Routine.create()를 companion object로 변경