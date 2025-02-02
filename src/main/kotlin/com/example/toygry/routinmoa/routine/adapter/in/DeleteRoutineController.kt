package com.example.toygry.routinmoa.routine.adapter.`in`

import com.example.toygry.routinmoa.routine.application.port.`in`.DeleteRoutineUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/routine")
class DeleteRoutineController(
    val deleteRoutineUseCase: DeleteRoutineUseCase
) {

    // 루틴 삭제 (삭제한 루틴 id return)
    @DeleteMapping("/{userId}/{routineId}")
    fun deleteRoutine(@PathVariable userId: Long, @PathVariable routineId: Long): ResponseEntity<Long> {
        val deleteId = deleteRoutineUseCase.deleteRoutine(userId, routineId)
        return ResponseEntity.ok(deleteId)
    }
}