package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.UserDto
import com.example.springdemo.dto.UserTodoEntityDTO
import com.example.springdemo.entity.UserEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
@Component
interface UserTodoMapper {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(source = "user.createdAt", target = "userCreatedAt"),
        Mapping(source = "todo.createdAt", target = "todoCreatedAt"),
        Mapping(source = "todo.updatedAt", target = "todoUpdatedAt"),
    )
    fun userTodoEntityDTO(todo: TodoDto, user: UserDto): UserTodoEntityDTO

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(source = "user.createdAt", target = "userCreatedAt"),
        Mapping(source = "todo.createdAt", target = "todoCreatedAt"),
        Mapping(source = "todo.updatedAt", target = "todoUpdatedAt"),
    )
    fun writeUserTodoEntityDTO(todo: TodoDto, user: UserDto, @MappingTarget userTodoEntityDTO: UserTodoEntityDTO): Unit
}
