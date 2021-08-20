package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.UserDto
import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import org.apache.catalina.User
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring",uses=[TodoMapper::class])
@Component
interface TodoMapper : EntityMapper<TodoDto,TodoEntity>{

    override fun toDto(todo: TodoEntity): TodoDto

    @InheritInverseConfiguration
    override fun toEntity(todoDto: TodoDto): TodoEntity

}
