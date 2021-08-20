package com.example.springdemo.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_board")
data class BoardEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var boardno: Long? = null) {

    var title: String?= null

    @ManyToOne
    @JoinColumn(name = "userno")
    var user : UserEntity? = null

    var createAt : LocalDateTime? = null
}
