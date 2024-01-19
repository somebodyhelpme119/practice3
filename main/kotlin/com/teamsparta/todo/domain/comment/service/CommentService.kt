package com.teamsparta.todo.domain.comment.service

import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest

interface CommentService {

    fun getComment(commentId: Long): CommentResponse

    fun createComment(commentId: Long, todoId: Long, request: CreateTodoRequest): CommentResponse

    fun updateComment(commentId: Long, todoId: Long, request: UpdateTodoRequest): CommentResponse

    fun deleteComment(commentId: Long, todoId: Long)
}