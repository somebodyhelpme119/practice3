package com.teamsparta.todo.entity


import com.teamsparta.todo.domain.todo.dto.TodoResponse
import jakarta.persistence.*

@Entity
@Table(name = "todo")

 data class Todo(
    @Column(name = "title")
    var title : String ="",

    @Column(name = "maintext")
    var maintext : String = "",

    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comment: MutableList<Comment> = mutableListOf()
        //데이터  값들
) {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
        var id: Long? = null
    //id 자동생성 컬럼에 id값을 따로 넣어줄 필요가 없다.

    fun toResponse(): TodoResponse {
        return TodoResponse(
            title = title,
            maintext = maintext,
            id = id!!
        )
    }}



