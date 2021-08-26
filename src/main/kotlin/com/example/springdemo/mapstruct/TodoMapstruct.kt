package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.entity.TodoEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
@Component
interface TodoMapstruct {

    fun toDTO(todo: TodoEntity, @Context context: CycleAvoidingMappingContext): TodoDTO

    @InheritInverseConfiguration
    fun toEntity(todoDto: TodoDTO, @Context context: CycleAvoidingMappingContext): TodoEntity

}
