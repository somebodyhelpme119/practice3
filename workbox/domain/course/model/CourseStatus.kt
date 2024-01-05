package com.teamsparta.workbox.domain.course.model

enum class CourseStatus {
        OPEN,
    CLOSED
//여기에 다른게 들어가면 값이 바뀌어버리게된다
    //예  오픈은 0 이고 클로스드는 1인데  여기 사이  n이라는 값이 들어가버리면  오픈은 0 n은 1 클로즈드는 2가되어버려서
    //값이 바뀌게 된다  그래서 상위 course에 @Enumerated를 써준다.EnumType.STRING 타입은 스트링
}
