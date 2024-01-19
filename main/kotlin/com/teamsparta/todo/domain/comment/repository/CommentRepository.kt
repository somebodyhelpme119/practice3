package com.teamsparta.todo.domain.comment.repository

import com.teamsparta.todo.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>