package com.example.toygry.routinmoa.routine.application.port.out

import com.example.toygry.routinmoa.routine.domain.entity.Routine

interface CreateRoutinePort {
    fun createRoutine(routine: Routine): Routine
}