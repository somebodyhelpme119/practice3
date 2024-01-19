package com.teamsparta.todo.domain.user.dto

data class LoginResponse(
    val email : String,
    val accessToken: String
)
