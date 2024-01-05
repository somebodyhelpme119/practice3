package com.teamsparta.workbox.domain.course.dto



data class CreateCourseRequest(
    val title: String,
    val description: String?
)


//1. 강의 해서 하던것을 그대로 배껴서 만들고있는데
// 강의쪽에는 로그인부분이 있어서 user id 부분이 필요했으나 지금만들고있는 과제 부분에서는 user  id 부분이 필요없는데 지워도 상관없나요

//2.작성내용을 나만 수정할수있게 해야하는데 이건 어떻게 처리해야할까요