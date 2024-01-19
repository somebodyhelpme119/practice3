package com.teamsparta.todo.domain.todo.controller

import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todo")
@RestController
class TodoController (
    private val todoService: TodoService
){
  @GetMapping("/{todoId}")
fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse>{
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(todoService.getTodoById(todoId))
}

    @GetMapping()
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList())
    }
    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse>
    {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.createTodo(createTodoRequest))
    }
    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Long,
                   @RequestBody updateTodoRequest: UpdateTodoRequest
    )
    : ResponseEntity<TodoResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId,updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long):ResponseEntity<Unit>
    {todoService.deleteTodo(todoId)

            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }


}