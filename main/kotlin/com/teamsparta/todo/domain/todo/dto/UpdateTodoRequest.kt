package com.teamsparta.todo.domain.todo.dto

data class UpdateTodoRequest(
    val title : String,
    val maintext : String,
    val id : Long

)
