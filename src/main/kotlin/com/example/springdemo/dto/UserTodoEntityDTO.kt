package com.example.springdemo.dto

import com.example.springdemo.entity.TodoEntity
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


data class UserTodoEntityDTO(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null){
    var name: String? = null
    val todos = mutableListOf<TodoEntity>()

    var userCreatedAt: LocalDateTime? = null
    var userUpdatedAt: LocalDateTime? = null

    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var todoCreatedAt: LocalDateTime? = null
    var todoUpdatedAt: LocalDateTime? = null
    override fun toString(): String {
        return "UserTodoEntityDTO(id=$id, name=$name, todos=$todos, userCreatedAt=$userCreatedAt, userUpdatedAt=$userUpdatedAt, title=$title, description=$description, schedule=$schedule, todoCreatedAt=$todoCreatedAt, todoUpdatedAt=$todoUpdatedAt)"
    }

}
