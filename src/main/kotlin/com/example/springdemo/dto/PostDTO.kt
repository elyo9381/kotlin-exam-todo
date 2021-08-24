package com.example.springdemo.dto

import com.google.gson.JsonObject
import java.time.LocalDateTime


data class PostDTO(var postno: Long? = null) {
    var name: String? = null
    var title: String? = null

    var car: JsonObject? = null

    var createdDate: LocalDateTime? = null

    var price : Int ? =null

    override fun toString(): String {
        return "PostDto(postno=$postno, name=$name, title=$title, car=$car, createdDate=$createdDate)"
    }


}


