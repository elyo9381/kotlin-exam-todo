package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.entity.TodoEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
@Component
interface TodoMapper {

    fun toDTO(todo: TodoEntity): TodoDto

    @InheritInverseConfiguration
    fun toEntity(todoDto: TodoDto): TodoEntity

}
