package com.teamsparta.workbox.domain.course.service

import com.teamsparta.workbox.domain.course.dto.CourseResponse
import com.teamsparta.workbox.domain.course.dto.CreateCourseRequest
import com.teamsparta.workbox.domain.course.dto.UpdateCourseRequest
import com.teamsparta.workbox.domain.course.model.Course
import com.teamsparta.workbox.domain.course.model.CourseStatus
import com.teamsparta.workbox.domain.course.model.toResponse
import com.teamsparta.workbox.domain.course.repository.CourseRepository
import com.teamsparta.workbox.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.workbox.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.workbox.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.workbox.domain.courseapplication.model.CourseApplication
import com.teamsparta.workbox.domain.courseapplication.model.CourseApplicationStatus
import com.teamsparta.workbox.domain.courseapplication.model.toResponse
import com.teamsparta.workbox.domain.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.workbox.domain.exception.ModelNotFoundException
import com.teamsparta.workbox.domain.user.repository.UserRepository
import com.teamsparta.workbox.domain.work.dto.AddWorkRequest
import com.teamsparta.workbox.domain.work.dto.UpdateWorkRequest
import com.teamsparta.workbox.domain.work.dto.WorkResponse
import com.teamsparta.workbox.domain.work.model.Work
import com.teamsparta.workbox.domain.work.model.toResponse
import com.teamsparta.workbox.domain.work.repository.WorkRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val workRepository: WorkRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository,
): CourseService {
    override fun getAllCourseList(): List<CourseResponse> {

        return courseRepository.findAll().map { it.toResponse() }
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.toResponse()
    }
    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {

        return courseRepository.save(
            Course(
                title = request.title,
                description = request.description,
                status = CourseStatus.OPEN,
            )
        ).toResponse()
    }
    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val (title, description) = request

        course.title = title
        course.description = description

        return courseRepository.save(course).toResponse()
    }
    @Transactional
    override fun deleteCourse(courseId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)

    }

    override fun getWorkList(courseId: Long): List<WorkResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.works.map { it.toResponse() }
    }

    override fun getWork(courseId: Long, workId: Long): WorkResponse {
        val work = workRepository.findByCourseIdAndId(courseId, workId)
            ?: throw ModelNotFoundException("Work", workId)

        return work.toResponse()
    }
    @Transactional
    override fun addWork(courseId: Long, request: AddWorkRequest): WorkResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val work = Work(
            title = request.title,
            workurl = request.workUrl,
            course = course
        )
        course.addWork(work)
        courseRepository.save(course)
        return work.toResponse()
    }
    @Transactional
    override fun updateWork(courseId: Long, workId: Long, request: UpdateWorkRequest): WorkResponse {
        val work = workRepository.findByCourseIdAndId(courseId, workId)
            ?: throw ModelNotFoundException("Work", workId)

        val (title, workurl) = request
        work.title = title
        work.workurl = workurl

        return workRepository.save(work).toResponse()
    }
    @Transactional
    override fun removeWork(courseId: Long, workId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val work = workRepository.findByIdOrNull(workId)
            ?: throw ModelNotFoundException("Work", workId)

        course.removeWork(work)


        courseRepository.save(course)
    }
    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)


        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")

    }

        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(
            course = course,
            user = user,
        )
        course.addCourseApplication(courseApplication)

        courseRepository.save(course)

        return courseApplication.toResponse()
    }


    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        return course.courseApplications.map { it.toResponse() }
    }
    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)


        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }


        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }


        when (request.status) {
            // 승인 일때
            CourseApplicationStatus.ACCEPTED.name -> {
                // 승인 처리


                courseRepository.save(course)
            }

            // 거절 일때
            CourseApplicationStatus.REJECTED.name -> {
                // 거절 처리
                application.reject()
            }
            // 승인 거절이 아닌 다른 인자가 들어올 경우 에러 처리
            else -> {
                throw IllegalArgumentException("Invalid status: ${request.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }

}