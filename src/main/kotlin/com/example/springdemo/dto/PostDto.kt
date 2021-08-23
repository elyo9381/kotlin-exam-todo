package com.example.springdemo.dto

import com.example.springdemo.common.JsonConver
import com.google.gson.JsonObject
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Convert


data class PostDto(var postno: Long? = null) {
    var name: String? = null
    var title: String? = null

    var car: JsonObject? = null

    var createdDate: LocalDateTime? = null

    override fun toString(): String {
        return "PostDto(postno=$postno, name=$name, title=$title, car=$car, createdDate=$createdDate)"
    }


}


