package com.example.toygry.routinmoa.user.adapter.`in`

import com.example.toygry.routinmoa.user.application.port.`in`.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/user")
class UserController(
    val userUseCase: UserUseCase
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user = userUseCase.getUser(id)
        val userResponse = UserResponse(user.id, user.email, user.name, user.level, user.points, user.createdAt)
        return ResponseEntity.ok(userResponse)
    }

//    @PostMapping
//    fun signUp(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
//    }


}

data class UserRequest(val email: String, val name: String)
data class UserResponse(val id:Long, val email: String, val name: String, val level:Int, val points:Int, val createdAt: LocalDateTime)
