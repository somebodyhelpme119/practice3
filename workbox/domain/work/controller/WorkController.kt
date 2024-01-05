package com.teamsparta.workbox.domain.work.controller

import com.teamsparta.workbox.domain.course.service.CourseService
import com.teamsparta.workbox.domain.work.dto.AddWorkRequest
import com.teamsparta.workbox.domain.work.dto.UpdateWorkRequest
import com.teamsparta.workbox.domain.work.dto.WorkResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses/{courseId}/works")
@RestController
class WorkController(
    private val courseService: CourseService
) {


    @GetMapping
    fun getWorkList(@PathVariable courseId: Long): ResponseEntity<List<WorkResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getWorkList(courseId))
    }



@GetMapping("/{workId}")
fun getWork(@PathVariable courseId: Long, @PathVariable workId: Long): ResponseEntity<WorkResponse> {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(courseService.getWork(courseId, workId))
    }


@PostMapping
fun addWork(
    @PathVariable courseId: Long,
    @RequestBody addWorkRequest: AddWorkRequest
): ResponseEntity<WorkResponse> {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(courseService.addWork(courseId, addWorkRequest))
}

@PutMapping("/{workId}")
fun updateWork(
    @PathVariable courseId: Long,
    @PathVariable workId: Long,
    @RequestBody updateWorkRequest: UpdateWorkRequest
): ResponseEntity<WorkResponse> {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(courseService.updateWork(courseId, workId, updateWorkRequest))
}



    @DeleteMapping("/{workId}")
    fun removeWork(@PathVariable courseId: Long,
                   @PathVariable workId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}