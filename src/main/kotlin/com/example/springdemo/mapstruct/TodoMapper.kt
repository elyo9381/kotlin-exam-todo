package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.entity.Todo
import org.mapstruct.*
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring", imports = [DateTimeFormat::class])
@Component
interface TodoMapper {

    @Mappings(
        Mapping(source = "title", target = "titleName"),
        Mapping(target = "id",ignore = true)
    )
    fun toTodoDto(todo: Todo): TodoDto

    @Mappings(
        Mapping(source = "titleName", target = "title"),
        Mapping(target = "schedule", source = "schedule", dateFormat = "yyyy-MM-dd HH:mm:ss")
    )
    fun toTodo(todoDto: TodoDto): Todo

}
