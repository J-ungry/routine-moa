package com.example.toygry.routinmoa.user.adapter.out.persistence

import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import com.example.toygry.routinmoa.user.application.port.out.SignPort
import com.example.toygry.routinmoa.user.domain.vo.SignResult
import org.springframework.stereotype.Component

@Component
class SignAdapter(
    val userRepository: UserRepository
): SignPort {
    override fun login(email: String): SignResult {
        val userEntity = userRepository.findByEmail(email)
        if (userEntity != null) {
            return SignResult(true, "로그인")
        } else {
            return SignResult(false, "존재하지 않는 유저입니다")
        }
    }

    override fun signUp(email: String): SignResult {
        val userEntity = userRepository.findByEmail(email)
        if (userEntity == null) {
            return SignResult(true, "회원가입")
        } else {
            return SignResult(false, "이미 존재하는 유저입니다")
        }
    }
}