package com.example.springdemo.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class TodoDto(var id: Long? = null) {
    @field:NotBlank
    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
}


