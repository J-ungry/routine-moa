package com.example.toygry.routinmoa.routine.adapter.out.persistence

import com.example.toygry.routinmoa.routine.adapter.out.persistence.repository.RoutineRepository
import com.example.toygry.routinmoa.routine.application.port.out.CompleteRoutinePort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class RoutineCompleteAdapter(
    val routineRepository: RoutineRepository
):CompleteRoutinePort {
    @Transactional
    override fun complete(routineId: Long): String {
        // 일단 루틴 가져오기
        val routine = routineRepository.findById(routineId).orElseThrow{ IllegalArgumentException("routine not found") }

        // orElseThrow 를 하면 이미 RoutineEntity 니까 get() 을 안해도 됨 Optional 아님
        if (routine.isSuccess) {
            throw IllegalArgumentException("Routine is already success")
        } else if (routine.lastComplete != null && routine.lastComplete?.toLocalDate() == LocalDate.now()) {
            throw IllegalArgumentException("Routine has already been completed today")
        }

        routine.complete()
        return "success"
    }
}