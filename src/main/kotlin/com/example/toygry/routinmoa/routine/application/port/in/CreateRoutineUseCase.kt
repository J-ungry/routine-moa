package com.example.toygry.routinmoa.routine.application.port.`in`

import com.example.toygry.routinmoa.routine.domain.entity.Routine

interface CreateRoutineUseCase {
    fun createRoutine(routine: Routine): Routine
}