package com.teamsparta.workbox.domain.course.dto



data class CourseResponse(
    val id : Long,
    val title : String,
    val description : String?,
    val status: String,


)

//할일가져와야하는건
//아이디 (필수),  타이틀 (필수) , 설명 , 상태 , 할일의 정보?

