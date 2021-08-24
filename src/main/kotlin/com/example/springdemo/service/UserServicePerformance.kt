//package com.example.springdemo.service
//
//import com.example.springdemo.dto.UserDto
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.context.annotation.Primary
//import org.springframework.stereotype.Service
//
//@Service
//@Primary
//class UserServicePerformance : UserService{
//
//    private val log = LoggerFactory.getLogger(UserServicePerformance::class.java)
//
//    @Autowired
//    @Qualifier("userServiceImpl")
//    private lateinit var userService: UserService
//
//    private fun before(): Long {
//        return System.currentTimeMillis()
//    }
//
//    private fun after(start: Long) {
//        val end = System.currentTimeMillis()
//        log.info("userSave 수행 시간 : {} " , (end - start))
//    }
//
//    override fun userSave(userDto: UserDto): UserDto {
//        return userService.userSave(userDto)
//    }
//
//    override fun getUsers(): MutableList<UserDto> {
//        val start = before()
//        val users: MutableList<UserDto> = userService.getUsers()//구현은 자식 클래스에게 맡김
//        after(start)
//        return users
//    }
//
//
//}