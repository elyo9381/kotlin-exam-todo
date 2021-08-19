package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class TodoEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null)
{
    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
}


