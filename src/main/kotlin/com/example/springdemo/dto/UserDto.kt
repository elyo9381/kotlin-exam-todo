package com.example.springdemo.dto

import com.example.springdemo.entity.TodoEntity
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

data class UserDto(var userno: Long? = null) {
    var name: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    val todos: MutableList<TodoDto> = ArrayList()
    override fun toString(): String {
        return "UserDto(userno=$userno, name=$name, createdAt=$createdAt, updatedAt=$updatedAt, todos=$todos)"
    }


}


