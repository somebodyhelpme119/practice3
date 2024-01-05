package com.teamsparta.workbox.domain.work.repository

import com.teamsparta.workbox.domain.work.model.Work
import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository: JpaRepository<Work, Long> {

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): Work?
}