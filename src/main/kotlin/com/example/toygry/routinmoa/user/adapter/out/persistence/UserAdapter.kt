package com.example.toygry.routinmoa.user.adapter.out.persistence

import com.example.toygry.routinmoa.user.adapter.out.persistence.repository.UserRepository
import com.example.toygry.routinmoa.user.application.port.out.UserPort
import com.example.toygry.routinmoa.user.domain.entity.User
import org.springframework.stereotype.Component

@Component
class UserAdapter(val userRepository:UserRepository):UserPort {
    override fun getUser(id: Long): User {
        val userEntity =  userRepository.findById(id)
        if (userEntity.isPresent) {
            return User(userEntity.get().id,userEntity.get().email, userEntity.get().name, userEntity.get().level, userEntity.get().points, userEntity.get().createdAt)
        } else {
            throw IllegalArgumentException("User with id $id not found")
        }
    }
}