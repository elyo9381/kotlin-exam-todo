package com.example.springdemo.dto

import java.time.LocalDateTime

data class TodoPageDto(var title : String? = null, var description :String? = null) : SearchDTO(){
    var fromDate : LocalDateTime? = null
    var toDate : LocalDateTime? = null
}
