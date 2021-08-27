package com.example.springdemo.repository

import com.example.springdemo.entity.PostEntity
import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {


    @EntityGraph(attributePaths = ["todos"])
//    @Query(value = "select DISTINCT t from UserEntity t left join fetch t.todos")
    override fun findAll(): MutableList<UserEntity>
}