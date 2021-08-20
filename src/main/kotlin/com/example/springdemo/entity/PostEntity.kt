package com.example.springdemo.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_post")
data class PostEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var postno: Long? = null)
{

    var name: String? = null
    var title: String? = null

}



