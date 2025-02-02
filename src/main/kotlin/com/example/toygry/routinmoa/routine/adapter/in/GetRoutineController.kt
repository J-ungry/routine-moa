package com.example.toygry.routinmoa.routine.adapter.`in`

import com.example.toygry.routinmoa.routine.application.port.`in`.GetRoutineUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/routine")
class GetRoutineController(
    val getRoutineUseCase: GetRoutineUseCase
) {

    // 사용자 아이디로 routine 가져오기
    @GetMapping("/{id}")
    fun getRoutine(@PathVariable id: Long): ResponseEntity<List<RoutineResponse>>{
        // Routine 의 List 가져오기
        val routines = getRoutineUseCase.getRoutine(id)
        // Routine 을 routine response 로 변환
        val result = routines.map { routine -> RoutineResponse(
            id = routine.id,
            userId = routine.userId,
            name = routine.name,
            startDate = routine.startDate,
            endDate = routine.endDate,
            goalCount = routine.goalCount,
            completedCount = routine.completedCount,
            isDeleted = routine.isDeleted,
            isSuccess = routine.isSuccess,
            createdAt = routine.createdAt,
            updatedAt = routine.updatedAt
        ) }
        // return
        return ResponseEntity.ok(result)

    }
}

data class RoutineResponse(
    val id: Long,
    val userId: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val goalCount: Int,
    val completedCount: Int,
    val isDeleted: Boolean,
    val isSuccess: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)