package com.example.toygry.routinmoa.routine.application.port.out

interface DeleteRoutinePort {
    fun deleteRoutine(userId: Long, routineId: Long): Long
}