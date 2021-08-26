package com.example.springdemo.entity

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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class UserEntityTest @Autowired constructor(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val todoRepository: TodoRepository
) {
    var todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)
    var userMapstruct = Mappers.getMapper(UserMapstruct::class.java)
    private val log = LoggerFactory.getLogger(UserEntityTest::class.java)

    @Test
    fun userfindOne(){

        val findAll = todoRepository.findAll()

        val toMutableList = findAll.map { todoMapstruct.toDTO(it, CycleAvoidingMappingContext()) }.toMutableList()
        log.info("toMutableList : {}",toMutableList)

        val findById = userRepository.findById(1).get()
        log.info("findById : {}",findById)

        val toDto = userMapstruct.toDTO(findById, CycleAvoidingMappingContext())
        log.info("toDTO: {}",toDto.todos)
    }


    @Test
    fun userfindAll(){
        val toMutableList = userRepository.findAll()
            .map { userMapstruct.toDTO(it,CycleAvoidingMappingContext()) }
            .toMutableList()
        println(toMutableList)
    }
}