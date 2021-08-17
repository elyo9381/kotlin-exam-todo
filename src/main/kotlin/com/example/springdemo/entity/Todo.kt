package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Todo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var schedule: LocalDateTime? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = null,
){
    fun convertTodo(todoDto: TodoDto) : Todo {
        return this.apply {
            this.id = todoDto.id
            this.title = todoDto.titleName
            this.description = todoDto.description
//            this.schedule = LocalDateTime.parse(todoDto.schedule);
            this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            this.createdAt = todoDto.createdAt
            this.updatedAt = todoDto.updatedAt
        }
    }
}



