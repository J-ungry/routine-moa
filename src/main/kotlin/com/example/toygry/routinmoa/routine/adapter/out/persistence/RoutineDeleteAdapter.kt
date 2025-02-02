package com.example.toygry.routinmoa.routine.adapter.out.persistence

import com.example.toygry.routinmoa.routine.adapter.out.persistence.repository.RoutineRepository
import com.example.toygry.routinmoa.routine.application.port.out.DeleteRoutinePort
import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class RoutineDeleteAdapter(
    val routineRepository: RoutineRepository,
    val userRepository: UserRepository
): DeleteRoutinePort {

    @Transactional
    override fun deleteRoutine(userId:Long, routineId: Long): Long {
        // routine entity 가져와서
        val deleteRoutine = routineRepository.findById(routineId).orElseThrow {IllegalArgumentException("routine not found")}
        // 유저 유효한지 확인하고
        val loginUser = userRepository.findById(userId).orElseThrow {IllegalArgumentException("user not found")}

        if (deleteRoutine.user.id != loginUser.id) {
            throw IllegalArgumentException("작성한 사용자만 삭제할 수 있습니다")
        }
        // is deleted true 로 변경 후 save dirty check 로 인해서 알아서 업데이트 됨
        deleteRoutine.delete()

        // 그럼 이거 save 쓰면 쿼리 두번 도나 ? => 실험 결과 : 한번만 돈다
//        routineRepository.save(deleteRoutine)

        return deleteRoutine.id
    }
}