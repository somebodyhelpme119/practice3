package com.teamsparta.workbox.domain.course.service

import com.teamsparta.workbox.domain.course.dto.CourseResponse
import com.teamsparta.workbox.domain.course.dto.CreateCourseRequest
import com.teamsparta.workbox.domain.course.dto.UpdateCourseRequest
import com.teamsparta.workbox.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.workbox.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.workbox.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.workbox.domain.work.dto.AddWorkRequest
import com.teamsparta.workbox.domain.work.dto.UpdateWorkRequest
import com.teamsparta.workbox.domain.work.dto.WorkResponse

interface CourseService {

    fun getAllCourseList(): List<CourseResponse>

    fun getCourseById(courseId: Long):  CourseResponse

    fun  createCourse(request: CreateCourseRequest): CourseResponse

    fun  updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun  completeCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun  deleteCourse(courseId: Long)

    fun  getWorkList(courseId: Long): List<WorkResponse>

    fun  getWork(courseId: Long, workId: Long): WorkResponse

    fun  addWork(courseId: Long, request: AddWorkRequest): WorkResponse

    fun updateWork(
        courseId: Long,
        workId: Long,
        request: UpdateWorkRequest
    ): WorkResponse

    fun removeWork(courseId: Long, workId: Long)

    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse
}