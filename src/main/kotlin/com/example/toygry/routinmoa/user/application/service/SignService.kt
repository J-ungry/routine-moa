package com.example.toygry.routinmoa.user.application.service

import com.example.toygry.routinmoa.user.application.port.`in`.SignUseCase
import com.example.toygry.routinmoa.user.application.port.out.SignPort
import com.example.toygry.routinmoa.user.domain.vo.SignResult
import org.springframework.stereotype.Service

@Service
class SignService(
    val signPort: SignPort
):SignUseCase {

    override fun sendEmail(email: String, type: String): SignResult {
        return signPort.sendEmail(email, type)
    }

    override fun verifyCode(email: String, code: String, type: String): SignResult {
        // 코드가 맞는지 확인하기
        return signPort.verifyCode(email, code, type)
    }

    override fun setName(email: String, name: String): SignResult {
        return signPort.setName(email, name)
    }

    override fun sign(email: String): SignResult {
        return signPort.sign(email)
    }
}