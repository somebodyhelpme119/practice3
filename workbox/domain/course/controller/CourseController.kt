package com.teamsparta.workbox.domain.course.controller

import com.teamsparta.workbox.domain.course.dto.CourseResponse
import com.teamsparta.workbox.domain.course.dto.CreateCourseRequest
import com.teamsparta.workbox.domain.course.dto.UpdateCourseRequest
import com.teamsparta.workbox.domain.course.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses")
@RestController
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping()
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getAllCourseList())
    }

    @GetMapping("/{courseId}")
    fun getCourse(@PathVariable courseId: Long):ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    @PostMapping
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity <CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")
    fun updateCourse(@PathVariable courseId: Long,
                     @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @PutMapping
    fun completeCourse2 (@PathVariable courseId: Long,
                       @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CourseService.completeCourse(courseId, updateCourseRequest)
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit>{
        courseService.deleteCourse(courseId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}