package com.teamsparta.todo.domain.user.repository

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<SecurityProperties.User, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): SecurityProperties.User?
}