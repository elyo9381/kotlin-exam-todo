package com.example.springdemo.dto

import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.UserMapstruct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.time.LocalDateTime


internal class testUserDTO {

    val userMapstruct = Mappers.getMapper(UserMapstruct::class.java)


    @Test
    @DisplayName("DTO에서 Entity로 변환하는 테스트")
    fun `test dto to entity`() {
        /* given */
        val testDto = UserDTO().apply {
            this.userno= null
            this.name = "title"
            this.createdAt = null
            this.updatedAt = null
        }
        /* when */
        val toEntity = userMapstruct.toEntity(testDto,CycleAvoidingMappingContext())

        assertThat(toEntity).isNotNull
        assertEquals(null , testDto.userno)
        assertEquals("title" , testDto.name)

    }

    @Test
    @DisplayName("Entity서 Dto로 변환하는 테스트")
    fun `test entity to dto`() {
        /* given */
        val entity = UserEntity().apply {
            this.userno= 1
            this.name = "test name"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = null
        }


        val dataEntity = TodoEntity().apply {
            this.todono = 1
            this.title = "test todo won~~"
            this.description = "base test todo description"
            this.schedule = LocalDateTime.now()
        }

        entity.addTodo(dataEntity)

        /* when */
        val toTodoDto = userMapstruct.toDTO(entity, CycleAvoidingMappingContext())


        /* then */
        assertThat(toTodoDto).isNotNull
        assertEquals(1 , toTodoDto.userno)
        assertEquals("test name" , toTodoDto.name)
        assertEquals( 1, toTodoDto.todos.size)
        assertEquals( "test todo won~~", toTodoDto.todos[0].title)
        assertEquals( "base test todo description", toTodoDto.todos[0].description)
    }

}