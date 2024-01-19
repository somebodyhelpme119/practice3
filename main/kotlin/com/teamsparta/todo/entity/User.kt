package com.teamsparta.todo.entity

import com.teamsparta.todo.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Column(name = "email")
    var email : String
) {

//    @Id
//    @GeneratedValue(
//        strategy = GenerationType.IDENTITY)
//    var id: Long? = null


    fun toResponse(): UserResponse {
        return UserResponse(
            email = email
        )
    }
}