package com.example.toygry.routinmoa.user.application.service

import com.example.toygry.routinmoa.user.application.port.`in`.SignUseCase
import com.example.toygry.routinmoa.user.application.port.out.SignPort
import com.example.toygry.routinmoa.user.domain.vo.SignResult
import org.springframework.stereotype.Service

@Service
class SignService(
    val signPort: SignPort
):SignUseCase {
    override fun login(email: String): SignResult {
        return signPort.login(email)
    }

    override fun signUp(email: String): SignResult {
        return signPort.signUp(email)
    }

    override fun sendEmail(email: String, type: String): SignResult {
        return signPort.sendEmail(email, type)
    }
}