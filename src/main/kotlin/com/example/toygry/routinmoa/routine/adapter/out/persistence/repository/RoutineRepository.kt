package com.example.toygry.routinmoa.routine.adapter.out.persistence.repository

import com.example.toygry.routinmoa.routine.adapter.out.persistence.entity.RoutineEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface RoutineRepository: JpaRepository<RoutineEntity, Long> {

    fun getAllByUserId(userId: Long): List<RoutineEntity>
}