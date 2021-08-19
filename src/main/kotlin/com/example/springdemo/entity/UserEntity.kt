package com.example.springdemo.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null)
{
    var name: String? = null

    @OneToMany
    @JoinColumn(name = "todo_entity.id")
    var todos = mutableListOf<TodoEntity>()



    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    fun addTodos(todoEntity: TodoEntity) {
        this.todos.add(todoEntity)
    }
}



