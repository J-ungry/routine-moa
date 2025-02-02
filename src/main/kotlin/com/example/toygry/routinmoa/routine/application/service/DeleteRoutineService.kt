package com.example.toygry.routinmoa.routine.application.service

import com.example.toygry.routinmoa.routine.application.port.`in`.DeleteRoutineUseCase
import com.example.toygry.routinmoa.routine.application.port.out.DeleteRoutinePort
import org.springframework.stereotype.Service

@Service
class DeleteRoutineService(
    val deleteRoutinePort: DeleteRoutinePort
): DeleteRoutineUseCase {
    override fun deleteRoutine(userId: Long, routineId: Long): Long {
        return deleteRoutinePort.deleteRoutine(userId, routineId)
    }
}