package com.example.toygry.routinmoa.routine.adapter.out.persistence

import com.example.toygry.routinmoa.routine.adapter.out.persistence.repository.RoutineRepository
import com.example.toygry.routinmoa.routine.application.port.out.GetRoutinePort
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class GetRoutineAdapter(
    val routineRepository: RoutineRepository
): GetRoutinePort {
    override fun getRoutine(userId: Long): List<Routine> {
        return routineRepository.getAllByUserId(userId)
            .map {it.toDomain()}
    }
}