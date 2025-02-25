package com.example.toygry.routinmoa.user.application.port.`in`

import com.example.toygry.routinmoa.user.domain.vo.SignResult

interface SignUseCase {
    fun login(email: String): SignResult
    fun signUp(email: String): SignResult
    fun sendEmail(email: String, type: String): SignResult
    fun verifyCode(email: String, code: String): SignResult
    fun setName(email: String, name: String): SignResult
}