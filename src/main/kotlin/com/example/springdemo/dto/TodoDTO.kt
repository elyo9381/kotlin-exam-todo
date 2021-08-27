package com.example.springdemo.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class TodoDTO(var todono: Long? = null) {
    @field:NotBlank
    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var user : UserDTO? = null

    override fun toString(): String {
        return "TodoDto(todono=$todono, title=$title, description=$description, schedule=$schedule, createdAt=$createdAt, updatedAt=$updatedAt, user=$user)"
    }
}


