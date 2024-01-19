package com.teamsparta.todo.domain.todo.service


import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.repository.TodoRepository
import com.teamsparta.todo.entity.Todo
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TodoServiceImpl (
    private val todoRepository : TodoRepository
): TodoService
{
    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw Exception("error.")
        return todo.toResponse()
    }
@Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                title = request.title,
                maintext = request.maintext,

            )
        ).toResponse()
    }
@Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw Exception("error.")
        val (title,maintext,id) = request

        todo.title = title
        todo.maintext = maintext
        todo.id = id

        return todoRepository.save(todo).toResponse()
    }
@Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw Exception("error")
        todoRepository.delete(todo)
    }

}