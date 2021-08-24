package com.example.springdemo.dto

import java.time.LocalDateTime
import kotlin.collections.ArrayList

data class UserDTO(var userno: Long? = null) {
    var name: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    val todos: MutableList<TodoDTO> = ArrayList()
    override fun toString(): String {
        return "UserDto(userno=$userno, name=$name, createdAt=$createdAt, updatedAt=$updatedAt, todos=$todos)"
    }


}

