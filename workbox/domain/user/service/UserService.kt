package com.teamsparta.workbox.domain.user.service

import com.teamsparta.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.workbox.domain.user.dto.SignUpRequest

interface UserService {

    fun signUp(signUpRequest: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse

}