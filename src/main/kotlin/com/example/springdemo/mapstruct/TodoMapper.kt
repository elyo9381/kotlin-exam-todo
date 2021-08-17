package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.entity.Todo
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
interface TodoMapper {

    @Mappings(
        Mapping(source = "title", target = "titleName"),
//        Mapping(target = "id",ignore = true)
    )
    fun toTodoDto(todo: Todo): TodoDto

    @InheritInverseConfiguration
    fun toTodo(todoDto: TodoDto): Todo
}
