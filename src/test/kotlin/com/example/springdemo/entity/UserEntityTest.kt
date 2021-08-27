package com.example.springdemo.entity

import com.example.springdemo.dto.UserDTO
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.PostMapstruct
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDateTime

@DataJpaTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class UserEntityTest @Autowired constructor(
    @Autowired private val userRepository: UserRepository
) {
    var userMapstruct = Mappers.getMapper(UserMapstruct::class.java)
    private val log = LoggerFactory.getLogger(UserEntityTest::class.java)

    @Test
    fun saveUSer(){
        val userDTO = UserDTO().apply {
            this.name = "won1"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        val userData = userMapstruct.toEntity(userDTO, CycleAvoidingMappingContext())
        val savedUser = userRepository.save(userData)

        assertNotNull(savedUser)
        assertEquals("won1", savedUser.name)
    }


    @Test
    fun userfindOne(){

        val findByIdInDB = userRepository.findById(1).get()
        log.info("findByIdInDB : {}",findByIdInDB)

        val toDto = userMapstruct.toDTO(findByIdInDB, CycleAvoidingMappingContext())
        println(toDto.todos[0].user?.todos?.get(0)?.user)
        log.info("first user of todos : {}", toDto.todos[0].user?.userno ?: "user null")
    }


    @Test
    fun userfindAll(){
        val toMutableList = userRepository.findAll()
            .map { userMapstruct.toDTO(it,CycleAvoidingMappingContext()) }
            .toMutableList()
        println(toMutableList)
    }
}