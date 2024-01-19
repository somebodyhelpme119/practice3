package com.teamsparta.todo.domain.comment.service


import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.repository.CommentRepository
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.repository.TodoRepository
import com.teamsparta.todo.entity.Comment
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentImpl (
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
    // 메인인 todo 와 comment를 연결시켜줘야한다
): CommentService
{
    override fun getComment(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw  Exception("댓글이 조회되지 않습니다.")
        return  comment.toResponse()
    }
@Transactional
    override fun createComment(commentId: Long, todoId: Long, request: CreateTodoRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(commentId) ?: throw Exception("error.") //todo 를 어떻게 인포트를 시켜줄까. todo랑 comment를 entitiy에서 연결.
    // 댓글은 todo에서 쓰는거기 때문에 todo를 써준다
        return commentRepository.save(
            Comment(
                title = request.title,
                maintext = request.maintext,

            )
        ).toResponse()
    }
@Transactional
    override fun updateComment(commentId: Long, todoId: Long, request: UpdateTodoRequest): CommentResponse {
        //1.comment id로 댓긓을 불러오고
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw Exception("ERROR")

        //2. 불러온 댓글을 request에 있는 내용으로 바꿔준다
        comment.title = request.title
        comment.maintext = request.maintext
        //3. 데이터 베이스에 저장해준다.
    return commentRepository.save(
        Comment(

        )

    ).toResponse()


        // 4. commentresponse로 리턴을 해줘야한다
    }


@Transactional
    override fun deleteComment(commentId: Long, todoId: Long) {
        //1 . TODO라는 객체와 COMMNET라는 객체를 찾아서 불러오고
        val todo = todoRepository.findByIdOrNull(todoId)?: throw Exception("게시글이 조회되지 않습니다.")
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw Exception("원 댓글을 찾을 수 없습니다.")
        //2.이것둘을 삭제해야한다.(todo, comment)
    todoRepository.delete(todo)
    commentRepository.delete(comment)
    }

}