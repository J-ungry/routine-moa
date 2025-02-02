package com.example.toygry.routinmoa.routine.adapter.out.persistence

import com.example.toygry.routinmoa.routine.adapter.out.persistence.repository.RoutineRepository
import com.example.toygry.routinmoa.routine.application.port.out.GetRoutinePort
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import org.springframework.stereotype.Component

@Component
class RoutineGetAdapter(
    val routineRepository: RoutineRepository
): GetRoutinePort {
    override fun getRoutine(userId: Long): List<Routine> {
        // TODO 삭제상태 아닌거만 가져오기
        return routineRepository.getAllByUserId(userId)
            .map {it.toDomain()}
    }
}