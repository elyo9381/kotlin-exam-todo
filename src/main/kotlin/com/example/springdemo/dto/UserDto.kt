package com.example.springdemo.dto

import java.time.LocalDateTime

data class UserDto(var id: Long? = null) {
    var name: String? = null

    var todos = mutableListOf<TodoDto>()

    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    fun addTodos(todoDto: TodoDto) {
        this.todos.add(todoDto)
    }
}


