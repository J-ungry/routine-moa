package com.example.toygry.routinmoa.routine.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class RoutineTest() {

    @Test
    fun `create should make correct initial values Routine`() {

        // given
        val userId: Long = 1
        val name: String = "test routine"
        val startDate: LocalDateTime = LocalDateTime.now()
        val endDate: LocalDateTime = LocalDateTime.now().plusDays(1)
        val goalCount: Int = 30

        // when
        val routine = Routine.create(userId, name, startDate, endDate, goalCount)
        // then
        assertThat(routine.id).isEqualTo(0)
        assertThat(routine.userId).isEqualTo(userId)
        assertThat(routine.name).isEqualTo("test routine")
        assertThat(routine.startDate).isEqualTo(startDate)
        assertThat(routine.endDate).isEqualTo(endDate)
        assertThat(routine.goalCount).isEqualTo(goalCount)
        assertThat(routine.completedCount).isEqualTo(0)
        assertThat(routine.isDeleted).isFalse()
        assertThat(routine.isSuccess).isFalse()

    }
}