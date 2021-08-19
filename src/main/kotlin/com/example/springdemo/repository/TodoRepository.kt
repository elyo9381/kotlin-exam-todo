package com.example.springdemo.repository

import com.example.springdemo.entity.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<TodoEntity, Long> {
    fun findByTitle(title: String?) : TodoEntity?
}