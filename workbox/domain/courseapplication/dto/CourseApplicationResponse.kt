package com.teamsparta.workbox.domain.courseapplication.dto

import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.workbox.domain.course.dto.CourseResponse



data class CourseApplicationResponse (
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val  status: String
// Courseresponse를 부르면 컬스에 모든정보값을 다담을수 있다.
)
