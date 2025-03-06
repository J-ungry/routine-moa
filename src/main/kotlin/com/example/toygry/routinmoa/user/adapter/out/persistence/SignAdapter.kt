package com.example.toygry.routinmoa.user.adapter.out.persistence

import com.example.toygry.routinmoa.token.application.port.TokenPort
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
import java.time.Duration
import java.time.LocalDateTime

@Component
class SignAdapter(
    val userRepository: UserRepository,
    val emailVerificationRepository: EmailVerificationRepository,
    private val mailSender: JavaMailSender,
    private val tokenPort: TokenPort
): SignPort {

    override fun sign(email: String): SignResult {
        val userEntity = userRepository.findByEmail(email)
        if (userEntity != null) {
            return SignResult(status = true, message = "로그인", type="login")
        } else {
            return SignResult(status = true, message = "회원가입", type="signUp")
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

        return SignResult(true, "이메일 전송 완료",type = type)
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
        if (type.equals("login")) {
            helper.setSubject("Routine Moa Verification Code")
        } else if (type.equals("signUp")) {
            helper.setSubject("Routine Moa Sign up Code")
        }
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

    override fun verifyCode(email: String, code: String, type: String): SignResult {
        // (1) 해당 이메일의 최신 인증 코드 조회
        val latestVerification = emailVerificationRepository.findTopByEmailOrderByCreatedAtDesc(email)
            ?: throw IllegalArgumentException("email not found")

        // code 정상인지, 10분 이상 지나지 않았는지 check
        if (latestVerification.code != code) {
            throw IllegalArgumentException("Invalid verification code")
        }

        if (Duration.between(latestVerification.createdAt, LocalDateTime.now()).toMinutes() > 10) {
            throw IllegalArgumentException("Verification code has expired.")
        }

        val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException("User not found")

        val token = tokenPort.generateToken(user.id, user.email, user.name)

        return SignResult(true, "Verification successful", token, type)
    }
}