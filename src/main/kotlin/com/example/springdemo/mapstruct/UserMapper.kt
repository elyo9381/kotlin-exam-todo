package com.example.springdemo.mapstruct

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.UserDto
import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring",uses=[UserMapper::class])
@Component
interface UserMapper :  EntityMapper<UserDto,UserEntity>{


    override fun toDto(userEntity: UserEntity): UserDto

    @InheritInverseConfiguration
    override fun toEntity(userDto: UserDto): UserEntity

}

