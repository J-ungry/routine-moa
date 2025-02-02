package com.example.toygry.routinmoa.routine.application.port.out

import com.example.toygry.routinmoa.routine.domain.entity.Routine

interface GetRoutinePort {

    fun getRoutine(userId: Long): List<Routine>
}