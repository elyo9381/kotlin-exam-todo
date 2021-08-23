package com.example.springdemo.entity

import com.example.springdemo.common.JsonConver
import com.google.gson.JsonObject
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_post")
data class PostEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var postno: Long? = null)
{

    var name: String? = null
    var title: String? = null

    @Convert(converter = JsonConver::class)
    var car : JsonObject? = null

    var createdDate: LocalDateTime? = null


    override fun toString(): String {
        return "PostEntity(postno=$postno, name=$name, title=$title, car=$car, createdDate=$createdDate)"
    }


}



