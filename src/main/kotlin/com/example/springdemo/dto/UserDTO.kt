package com.example.springdemo.dto

import com.google.gson.JsonArray
import java.time.LocalDateTime
import kotlin.collections.ArrayList

data class UserDTO(var userno: Long? = null) {
    var name: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var info : JsonArray? = null
    var todos: MutableList<TodoDTO> = ArrayList()

    var todolist : JsonArray? = null
//    override fun toString(): String {
//        return "UserDTO(userno=$userno, name=$name, createdAt=$createdAt, updatedAt=$updatedAt, info=$info, todos=$todos, todolist=$todolist)"
//    }

}


