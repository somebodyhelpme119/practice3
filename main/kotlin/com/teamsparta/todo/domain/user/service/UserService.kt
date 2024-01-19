package com.teamsparta.todo.domain.user.service

import com.teamsparta.todo.domain.user.dto.LoginRequest
import com.teamsparta.todo.domain.user.dto.LoginResponse
import com.teamsparta.todo.domain.user.dto.SignUpRequest
import com.teamsparta.todo.domain.user.dto.UserResponse


interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse
}