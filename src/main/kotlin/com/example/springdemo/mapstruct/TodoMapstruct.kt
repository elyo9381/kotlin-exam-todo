package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.entity.TodoEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring",uses=[TodoMapstruct::class])
@Component
interface TodoMapstruct : EntityMapstruct<TodoDTO,TodoEntity>{

    override fun toDto(todo: TodoEntity): TodoDTO

    @InheritInverseConfiguration
    override fun toEntity(todoDto: TodoDTO): TodoEntity

}
