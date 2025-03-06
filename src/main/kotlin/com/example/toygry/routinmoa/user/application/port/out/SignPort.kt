package com.example.toygry.routinmoa.user.application.port.out

import com.example.toygry.routinmoa.user.domain.vo.SignResult

interface SignPort {
    fun sendEmail(email: String, type: String): SignResult
    fun verifyCode(email: String, code: String, type: String): SignResult
    fun setName(email: String, name: String): SignResult
    fun sign(email: String): SignResult
}