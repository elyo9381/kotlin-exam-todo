package com.example.springdemo.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_todo")
data class TodoEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var todono: Long? = null)
{
    var title: String? = null
    var description: String? = null
    var schedule: LocalDateTime? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    @ManyToOne()
    @JoinColumn(name = "userno")
    var user : UserEntity? = null
}


