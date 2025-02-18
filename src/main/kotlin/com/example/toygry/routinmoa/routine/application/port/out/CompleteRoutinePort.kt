package com.example.toygry.routinmoa.routine.application.port.out

interface CompleteRoutinePort {
    fun complete(routineId: Long): String
}