package com.example.toygry.routinmoa.routine.application.service

import com.example.toygry.routinmoa.routine.application.port.`in`.CreateRoutineUseCase
import com.example.toygry.routinmoa.routine.application.port.out.CreateRoutinePort
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import org.springframework.stereotype.Service

@Service
class CreateRoutineService(
    val createRoutinePort: CreateRoutinePort
): CreateRoutineUseCase {
    override fun createRoutine(routine: Routine): Routine {
        return createRoutinePort.createRoutine(routine)
    }
}