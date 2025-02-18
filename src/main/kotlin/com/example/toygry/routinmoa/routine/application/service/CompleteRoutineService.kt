package com.example.toygry.routinmoa.routine.application.service

import com.example.toygry.routinmoa.routine.application.port.`in`.CompleteRoutineUseCase
import com.example.toygry.routinmoa.routine.application.port.out.CompleteRoutinePort
import org.springframework.stereotype.Service

@Service
class CompleteRoutineService(
    val completeRoutinePost: CompleteRoutinePort
): CompleteRoutineUseCase {
    override fun completeRoutine(routineId: Long): String {
        return completeRoutinePost.complete(routineId)
    }
}