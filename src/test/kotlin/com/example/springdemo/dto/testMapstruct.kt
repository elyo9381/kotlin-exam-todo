package com.example.springdemo.dto

import com.example.springdemo.entity.Todo
import com.example.springdemo.mapstruct.TodoMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


internal class testMapstruct {

    val converter = Mappers.getMapper(TodoMapper::class.java)

    @Test
    @DisplayName("Entity서 Dto로 변환하는 테스트")
    fun `test entity to dto`() {
        /* given */
        val entity = Todo().apply {
            this.id = 1
            this.title = "title"
            this.description = "~~~blabla"
            this.schedule = LocalDateTime.now()
            this.createdAt = LocalDateTime.now()
            this.updatedAt = null
        }
        /* when */
        val toTodoDto = converter.toTodoDto(entity)
        /* then */
        println(toTodoDto)
        assertThat(toTodoDto).isNotNull
        assertEquals(entity.title, toTodoDto.titleName)
    }

    @Test
    @DisplayName("DTO에서 Entity로 변환하는 테스트")
    fun `test dto to entity`() {
        /* given */
        val testDto = TodoDto().apply {
            this.id = null
            this.titleName = "title"
            this.description = "~~~blabla"
            this.schedule = "2021-08-17 12:48:00"
            this.createdAt = null
            this.updatedAt = null
        }
        /* when */
        val toEntity = converter.toTodo(testDto)

//        LocalDateTime.parse(testDto.schedule);
//        val toEntity = Todo().convertTodo(testDto)
        /* then */
        println(toEntity)
        assertThat(toEntity).isNotNull
        assertEquals( testDto.titleName, toEntity.title)

    }
}