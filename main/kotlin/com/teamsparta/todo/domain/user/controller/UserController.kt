package com.teamsparta.todo.domain.user.controller

import com.teamsparta.todo.domain.user.dto.LoginRequest
import com.teamsparta.todo.domain.user.dto.LoginResponse
import com.teamsparta.todo.domain.user.dto.SignUpRequest
import com.teamsparta.todo.domain.user.dto.UserResponse
import com.teamsparta.todo.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/login")
    fun signIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))

    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }


}