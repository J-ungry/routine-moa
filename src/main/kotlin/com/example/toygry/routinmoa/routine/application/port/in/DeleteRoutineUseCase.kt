package com.example.toygry.routinmoa.routine.application.port.`in`

interface DeleteRoutineUseCase {
    fun deleteRoutine(userId:Long,routineId: Long): Long
}