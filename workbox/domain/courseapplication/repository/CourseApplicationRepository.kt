package com.teamsparta.workbox.domain.courseapplication.repository

import com.teamsparta.workbox.domain.courseapplication.model.CourseApplication
import org.springframework.data.jpa.repository.JpaRepository

interface CourseApplicationRepository: JpaRepository<CourseApplication, Long> {


    fun existsByCourseIdAndUserId(courseId: Long, userId: Long): Boolean

    fun findByCourseIdAndId(courseId: Long, id: Long): CourseApplication?
}