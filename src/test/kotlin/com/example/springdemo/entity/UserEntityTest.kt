package com.example.springdemo.entity

import com.example.springdemo.mapstruct.PostMapstruct
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class UserEntityTest @Autowired constructor(
    @Autowired private val userRepository: UserRepository
) {
    val userMapstruct = Mappers.getMapper(UserMapstruct::class.java)

    @Test
    fun userfindAll(){
        val toMutableList = userRepository.findAll()
            .map { userMapstruct.toDto(it) }
            .toMutableList()
        println(toMutableList)
    }
}