package com.example.springdemo.dto

import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.TodoMapstruct
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers


internal class testTodoDTO {

    val todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)

    @Test
    @DisplayName("Entity서 Dto로 변환하는 테스트")
    fun `test entity to dto`() {

        val createdTodoEntity = TodoEntity().apply {
            this.todono = 1
            this.title = "test title"
            this.description = "test description"
            this.schedule = null
            this.createdAt = null
            this.updatedAt = null
            this.user = null
        }

        val changedTodoDTO = todoMapstruct.toDTO(createdTodoEntity, CycleAvoidingMappingContext())

        assertNotNull(changedTodoDTO)
        assertEquals(1 , changedTodoDTO.todono)
        assertEquals("test title" , changedTodoDTO.title)
        assertEquals("test description", changedTodoDTO.description)
        assertEquals(null , changedTodoDTO.user)

    }

    @Test
    @DisplayName("DTO에서 Entity로 변환하는 테스트")
    fun `test dto to entity`() {

        val createdTodoDTO = TodoDTO().apply {
            this.todono = 1
            this.title = "test title"
            this.description = "test description"
            this.schedule = null
            this.createdAt = null
            this.updatedAt = null
            this.user = null
        }

        val changedTodoEntity = todoMapstruct.toEntity(createdTodoDTO, CycleAvoidingMappingContext())

        assertNotNull(changedTodoEntity)
        assertEquals(1 , changedTodoEntity.todono)
        assertEquals("test title" , changedTodoEntity.title)
        assertEquals("test description", changedTodoEntity.description)
        assertEquals(null , changedTodoEntity.user)
    }
}