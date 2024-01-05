package com.teamsparta.workbox.domain.work.model

import com.teamsparta.workbox.domain.course.model.Course
import com.teamsparta.workbox.domain.work.dto.WorkResponse
import jakarta.persistence.*

@Entity
@Table(name = "work")
class Work (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "work_url", nullable = false)
    var workurl: String,

    @ManyToOne ( fetch = FetchType.LAZY) // 포린키를 들고있어서 이쪽이 주인
    //1대 n관계면 1쪽에 mappedBy 표기

    @JoinColumn(name = "course_id")
var course: Course

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Work.toResponse(): WorkResponse {
    return WorkResponse(
        id = id!!,
        title = title,
        workUrl = workurl,
    )
}