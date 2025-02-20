package com.example.toygry.routinmoa.user.adapter.`in`

import com.example.toygry.routinmoa.user.application.port.`in`.SignUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class SignController(
    val signUseCase: SignUseCase
) {

    // login (유저 유무 확인 후 있음 통과)
    @PostMapping("/login")
    fun login(@RequestBody request: EmailRequest): ResponseEntity<SignResponse> {
        val result = signUseCase.login(request.email)
        val response = SignResponse(result.status, result.message)
        return ResponseEntity.ok(response)
    }
    // signUp (유저 유무 확인 후 없음 통과)

    @PostMapping("/signUp")
    fun signUp(@RequestBody request: EmailRequest): ResponseEntity<SignResponse> {
        val result = signUseCase.signUp(request.email)
        val response = SignResponse(result.status, result.message)
        return ResponseEntity.ok(response)
    }
    // sendEmail (위 과정 통과 후 code 생성 + email 전송 type 받기)

    @PostMapping("/code")
    fun sendEmail(@RequestBody request: VerifyCodeRequest): ResponseEntity<SignResponse> {
        val result = signUseCase.sendEmail(request.email, request.type)
        val response = SignResponse(result.status,result.message)
        return ResponseEntity.ok(response)
    }
    // verifiedCode (코드 체크 후 token 발급 type 에 따라 setting)
    // setName (이름 설정하기)


}

data class EmailRequest(val email: String)
data class SignResponse(val status: Boolean, val message: String)
data class VerifyCodeRequest(val email: String, val type: String) // TODO enum 으로 변경