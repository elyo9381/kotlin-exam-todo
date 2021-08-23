package com.example.springdemo.repository

import com.example.springdemo.entity.PostEntity
import com.example.springdemo.entity.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByTitle(title: String?) : PostEntity?
}