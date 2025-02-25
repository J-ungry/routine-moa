package com.example.toygry.routinmoa.user.adapter.out.persistence

import com.example.toygry.routinmoa.user.adapter.out.persistence.entity.EmailVerificationEntity
import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.EmailVerificationRepository
import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import com.example.toygry.routinmoa.user.application.port.out.SignPort
import com.example.toygry.routinmoa.user.domain.vo.SignResult
import jakarta.mail.internet.MimeMessage
import jakarta.transaction.Transactional
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class SignAdapter(
    val userRepository: UserRepository,
    val emailVerificationRepository: EmailVerificationRepository,
    private val mailSender: JavaMailSender
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

    @Transactional
    override fun sendEmail(email: String, type: String): SignResult {
        // 코드 생성
        val newCode = generateCode()

        // 기존거 지우고
        emailVerificationRepository.deleteByEmail(email)

        val verifyInfo = EmailVerificationEntity(email = email, code = newCode)
        emailVerificationRepository.save(verifyInfo)

        sendVerificationEmail(email,newCode,type)

        return SignResult(true, "이메일 전송 완료")
    }

    @Transactional
    override fun setName(email: String, name: String): SignResult {
        // email 가져와서
        val userEntity = userRepository.findByEmail(email)

        if (userEntity != null) {
            userEntity.changeName(name)
        } else {
            throw IllegalArgumentException("User not found")
        }

        return SignResult(true, "이름 설정 완료")
    }

    private fun generateCode(): String {
        return (100000..999999).random().toString() // 6자리 숫자 랜덤 생성
    }

    fun sendVerificationEmail(email: String, code: String, type: String) {
        val message: MimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setTo(email)
        helper.setSubject("Your Verification Code")
        helper.setText(
            """
            <html>
                <body>
                    <h3>Your verification code</h3>
                    <p>Your verification code is: <b>$code</b></p>
                    <p>Please enter this code to verify your email.</p>
                </body>
            </html>
            """.trimIndent(),
            true
        )

        mailSender.send(message)
    }
}