package com.example.toygry.routinmoa.routine.application.service

import com.example.toygry.routinmoa.routine.application.port.`in`.GetRoutineUseCase
import com.example.toygry.routinmoa.routine.application.port.out.GetRoutinePort
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import org.springframework.stereotype.Service

@Service
class GetRoutineService(
    val getRoutine: GetRoutinePort
): GetRoutineUseCase {
    override fun getRoutine(userId: Long): List<Routine> {
        return getRoutine.getRoutine(userId)
    }
}