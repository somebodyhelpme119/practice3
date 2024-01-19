package com.teamsparta.todo.domain.todo.repository

import com.teamsparta.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository


interface TodoRepository : JpaRepository<Todo, Long>