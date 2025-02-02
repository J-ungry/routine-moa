package com.example.toygry.routinmoa.routine.adapter.`in`

import com.example.toygry.routinmoa.routine.application.port.`in`.CreateRoutineUseCase
import com.example.toygry.routinmoa.routine.domain.entity.Routine
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/routine")
class CreateRoutineController(
    val createRoutineUseCase: CreateRoutineUseCase
) {

    // 루틴 생성
    @PostMapping
    fun createRoutine(@RequestBody request:CreateRoutineRequest): ResponseEntity<RoutineResponse> {
        val routine = Routine.create(request.userId, request.name, request.startDate, request.endDate, request.goalCount)
        val result = createRoutineUseCase.createRoutine(routine)
        val response = RoutineResponse(result.id, result.userId, result.name, request.startDate, request.endDate, request.goalCount, result.completedCount, result.isDeleted, result.isSuccess, result.createdAt, result.updatedAt)
        return ResponseEntity.ok(response)
    }
}

data class CreateRoutineRequest(
    val userId: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val goalCount: Int)

