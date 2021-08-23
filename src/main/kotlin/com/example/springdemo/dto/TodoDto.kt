package com.example.springdemo.dto

import com.example.springdemo.entity.UserEntity
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class TodoDto(var todono: Long? = null) {
    @field:NotBlank
    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var user : UserDto? = null
    override fun toString(): String {
        return "TodoDto(todono=$todono, title=$title, description=$description, schedule=$schedule, createdAt=$createdAt, updatedAt=$updatedAt, user=$user)"
    }
}


