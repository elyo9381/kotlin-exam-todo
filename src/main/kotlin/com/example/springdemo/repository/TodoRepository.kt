package com.example.springdemo.repository

import com.example.springdemo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByTitle(title: String?) : Todo?
}