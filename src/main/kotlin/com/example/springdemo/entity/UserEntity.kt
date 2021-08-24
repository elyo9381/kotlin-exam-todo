package com.example.springdemo.entity

import com.example.springdemo.common.JsonArrayConverter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "tb_user")
data class UserEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var userno: Long? = null) {
    var name: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    @Convert(converter = JsonArrayConverter::class)
    var info : JsonArray? = null

    @OneToMany(mappedBy = "user")
    var todos: MutableList<TodoEntity> = ArrayList()

    fun addTodo(todo: TodoEntity) {
        todos.add(todo)
        todo.user = this
    }

}



