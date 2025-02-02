package com.example.toygry.routinmoa.routine.adapter.out.persistence

import com.example.toygry.routinmoa.routine.adapter.out.persistence.entity.RoutineEntity
import com.example.toygry.routinmoa.routine.adapter.out.persistence.repository.RoutineRepository
import com.example.toygry.routinmoa.routine.application.port.out.CreateRoutinePort
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class RoutineCreateAdapter(
    val routineRepository: RoutineRepository,
    val userRepository: UserRepository
    ): CreateRoutinePort {
    override fun createRoutine(routine: Routine): Routine {
        // user 검색
        val user = userRepository.findById(routine.userId)
            .orElseThrow {IllegalArgumentException("User with id ${routine.userId} not found") }
        // routine entity 생성
        val routineEntity = RoutineEntity.fromDomain(routine, user)
        // routine entity 저장
        val savedEntity = routineRepository.save(routineEntity)
        // routine 으로 변환 후 return
        return savedEntity.toDomain()
    }
}