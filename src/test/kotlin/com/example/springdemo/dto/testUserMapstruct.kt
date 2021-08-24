package com.example.springdemo.dto

import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import com.example.springdemo.mapstruct.UserMapstruct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.time.LocalDateTime


internal class testUserMapstruct {

    val userMapstruct = Mappers.getMapper(UserMapstruct::class.java)

    @Test
    @DisplayName("Entity서 Dto로 변환하는 테스트")
    fun `test entity to dto`() {
        /* given */
        val entity = UserEntity().apply {
            this.userno= 1
            this.name = "title"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = null
        }


        val dataEntity = TodoEntity().apply {
            this.todono = 1
            this.title = "test title won~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
        }

//        entity.addTodo(dataEntity)

        /* when */
        val toTodoDto = userMapstruct.toDto(entity)
        /* then */
        println(toTodoDto)
        assertThat(toTodoDto).isNotNull
    }

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
        val toEntity = userMapstruct.toEntity(testDto)

        println(toEntity)
        assertThat(toEntity).isNotNull

    }
}