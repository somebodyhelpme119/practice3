package com.teamsparta.workbox.domain.user.model

import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.workbox.domain.courseapplication.model.CourseApplication
import jakarta.persistence.*


    @Entity
    @Table(name = "app_user")

    class User (
        @Column(name = "email", nullable = false)
        val email: String,

        @Column(name = "password", nullable = false)
        val password: String,

     @Embedded
     var profile: Profile,

        @Enumerated(EnumType.STRING)
        @Column(name ="role", nullable = false)
        val role: UserRole,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
        val courseApplications: MutableList<CourseApplication> = mutableListOf()



){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name
    )
}

//엠버더블과 엠베디드는 셋트
//엠버더블은 다른 엔티티를 종속될수있는애 엠베디드는 종속할떄
