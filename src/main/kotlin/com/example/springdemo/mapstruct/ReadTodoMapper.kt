package com.example.springdemo.mapstruct

import com.example.springdemo.dto.ReadTodoDto
import com.example.springdemo.entity.Todo
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
interface ReadTodoMapper {

    fun toDto(todo: Todo): ReadTodoDto
}
