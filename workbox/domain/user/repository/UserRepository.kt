package com.teamsparta.workbox.domain.user.repository

import com.teamsparta.workbox.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,Long> {

    fun existsByEmail(email: String): Boolean
}