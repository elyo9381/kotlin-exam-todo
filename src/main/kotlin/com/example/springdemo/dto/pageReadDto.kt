package com.example.springdemo.dto

import com.example.springdemo.annotation.StringFormatDateTime
import com.example.springdemo.entity.Todo
import com.example.springdemo.utils.Pagination
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.NotBlank

data class pageReadDto(

    var pageList: MutableList<Map<String?, Any?>?>? = ArrayList(),
    var pageInfo : PageDto? = null

)

