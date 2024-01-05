package com.teamsparta.workbox.domain.user.service

import com.teamsparta.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.workbox.domain.exception.ModelNotFoundException
import com.teamsparta.workbox.domain.user.dto.SignUpRequest
import com.teamsparta.workbox.domain.user.model.Profile
import com.teamsparta.workbox.domain.user.model.User
import com.teamsparta.workbox.domain.user.model.UserRole
import com.teamsparta.workbox.domain.user.model.toResponse
import com.teamsparta.workbox.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
    }
        return userRepository.save(
            User(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = request.password,
                profile = Profile(
                    nickname = request.nickname
                ),
                role = when (request.role) {
                    UserRole.STUDENT.name -> UserRole.STUDENT
                    UserRole.TUTOR.name -> UserRole.TUTOR
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }
    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )
        return userRepository.save(user).toResponse()
    }
}