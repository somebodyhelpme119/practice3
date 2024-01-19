package com.teamsparta.todo.domain.comment.controller


import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.service.CommentService
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo/{todoId}/comment")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping("/{commentId}")
    fun getComment(@PathVariable todoId: Long, @PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getComment(commentId))
    }

    @PostMapping
    fun createComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @RequestBody createTodoRequest: CreateTodoRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(commentId, todoId, createTodoRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, todoId, updateTodoRequest))
    }//객체명을 담은 변수명을 넣어야한다.

    @DeleteMapping
    fun deleteComment(@PathVariable todoId: Long, @PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}