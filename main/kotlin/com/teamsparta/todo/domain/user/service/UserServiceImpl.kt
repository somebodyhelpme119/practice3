package com.teamsparta.todo.domain.user.service

import com.teamsparta.todo.domain.user.dto.LoginRequest
import com.teamsparta.todo.domain.user.dto.LoginResponse
import com.teamsparta.todo.domain.user.dto.SignUpRequest
import com.teamsparta.todo.domain.user.dto.UserResponse
import com.teamsparta.todo.domain.user.repository.UserRepository
import com.teamsparta.todo.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    override fun login(request: LoginRequest): LoginResponse {
        // 로그인이라는 객체는 로그인 리퀘스틀 필요로 한다  : 실행하면 로그 리스폰스
        val user = userRepository.findByEmail(request.email) ?: throw Exception("error")
            //변수  USER = user라는 객체( userReopository에서  email값을찾아서 바꿈) email 값이 아니면 error
        if (passwordEncoder.matches(request.password, user.password) ) {
            // passwordEncoder 에서 비밀번호 메치를 했는데 유저 비밀번호가 아니면 throwexception 을 표기한다
            // 어떻게 해야하는지 모르겠다
            throw Exception("비밀번호가 틀림")
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                // LoginResponse (안 값이 있어야 LoginResponse 를 쓸수있는데)
                //LoginResponse 에 있는 값은  email과 token이다.
                email = user.email

            )
        )
    }

//    override fun signUp(request: SignUpRequest): UserResponse {
//        if (userRepository.existsByEmail(request.email)) {
//            // 유저리스폰스라는 객체는 레파지토리 안에 존재하는 이메일을 요구한다
//            throw Exception("Error")
//            //없으면 에러가 발생.
//        }
//
//        return userRepository.save(
//            User(
//                email = request.email,
//                password = passwordEncoder.encode(request.password),
//
//                ),
//
//            )
//        ).toResponse
//    }


    }
