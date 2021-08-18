package com.example.springdemo.dto

import com.example.springdemo.annotation.StringFormatDateTime
import com.example.springdemo.entity.Todo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.NotBlank

data class TodoDto(


    var id: Long? = null,
    @field:NotBlank
    var titleName: String? = null,

    var description: String? = null,

    @field:NotBlank
    @field:StringFormatDateTime(patten = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var schedule: String? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = null,
)


