package com.teamsparta.workbox.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Enumerated

@Embeddable
 class Profile (
    @Column(name = "nickname")
    var nickname: String,

    )