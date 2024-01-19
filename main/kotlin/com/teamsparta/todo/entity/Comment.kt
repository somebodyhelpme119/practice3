package com.teamsparta.todo.entity


import com.teamsparta.todo.domain.comment.dto.CommentResponse
import jakarta.persistence.*


@Entity
@Table(name = "comment")
data class Comment (
    @Column(name = "title")
    var title : String = "",

    @Column(name = "maintext")
    var maintext : String = "",



    @ManyToOne
@JoinColumn(name = "todo", nullable = false)
var todo: Todo = Todo()

){
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
    var id: Long? = null


    fun toResponse(): CommentResponse {
        return CommentResponse(
            title = title,
            maintext = maintext,
            id = id!!
        )
}}
