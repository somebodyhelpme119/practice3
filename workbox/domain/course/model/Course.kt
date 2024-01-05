package com.teamsparta.workbox.domain.course.model

import com.teamsparta.workbox.domain.course.dto.CourseResponse
import com.teamsparta.workbox.domain.courseapplication.model.CourseApplication
import com.teamsparta.workbox.domain.work.model.Work
import jakarta.persistence.*

@Entity
@Table(name = "course")
class Course (
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,



    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CourseStatus,
    //status 에 다른값이 들어갈수도 있으니 enum으로 설정한다


    @Column (name = "complete")
    var isCompleted : Boolean = false,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    var works: MutableList<Work> = mutableListOf(),

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    var courseApplications: MutableList<CourseApplication> = mutableListOf()

    // 이게 붙어있으면 하인 / 지연은 레이지 /즉시는 에거 / 보통은 레이지



) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    fun close() {
        status = CourseStatus.CLOSED
    }

    fun addWork(work: Work) {
        works.add(work)
    }

    fun removeWork(work: Work) {
        works.remove(work)
    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }


}


fun Course.toResponse(): CourseResponse {
    return CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.name,

    )
}
//양방향 관계