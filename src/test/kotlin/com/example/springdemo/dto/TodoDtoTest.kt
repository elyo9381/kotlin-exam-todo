package com.example.springdemo.dto

import com.example.springdemo.entity.Todo
import com.example.springdemo.mapstruct.TodoMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import javax.validation.Validation

internal class TodoDtoTest {

    @Autowired
    private lateinit var todoMapper : TodoMapper

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            this.titleName = "테스트"
            this.description = ""
            this.schedule = "$2020-10-20 13:00:00"
        }
        val result = validator.validate(todoDto)
        assertEquals(true, result.isEmpty())
    }

    @Test
    fun failValidateSchedule() {
        val todoDto = TodoDto().apply {
            this.titleName = "테스트"
            this.description = ""
            this.schedule = "2020-10-2 13:00:00"
        }
        val result = validator.validate(todoDto)
        assertEquals(false, result.isEmpty())
    }

    @Test
    fun failValidateTitle() {
        val todoDto = TodoDto().apply {
            this.titleName = ""
            this.description = ""
            this.schedule = "2020-10-25 13:00:00"
        }
        val result = validator.validate(todoDto)
        assertEquals(false, result.isEmpty())
    }

    @Test
    fun testConvertFromDtoToTodo() {
        val todoDto = TodoDto().apply {
            this.titleName = "test"
            this.description = ""
            this.schedule = "2020-10-25 13:00:00"
        }
        val result = validator.validate(todoDto)

        val convertTodo = todoMapper.toTodo(todoDto)

        convertTodo.apply {
            id = 1
            description = "~~~ test blabla"
            createdAt = LocalDateTime.now()
            updatedAt = LocalDateTime.now()
        }
        println(convertTodo)

        assertEquals(true, result.isEmpty())
        assertEquals(todoDto.titleName, convertTodo.title)
    }


}