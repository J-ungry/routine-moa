package com.example.toygry.routinmoa.routine.adapter.`in`

import com.example.toygry.routinmoa.routine.application.port.`in`.CompleteRoutineUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routine/complete")
class CompleteRoutineController(
    val completeRoutineUseCase: CompleteRoutineUseCase
) {

    // 루틴 완료
    @PutMapping("/{id}")
    fun completeMapping(@PathVariable id: Long): ResponseEntity<CompleteResponse> {
        val response = CompleteResponse(completeRoutineUseCase.completeRoutine(id))
        return ResponseEntity.ok(response);
    }
}

data class CompleteResponse(val result: String)