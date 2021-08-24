package com.example.springdemo.mapstruct

import com.example.springdemo.dto.UserDTO
import com.example.springdemo.entity.UserEntity
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring",uses=[UserMapstruct::class])
@Component
interface UserMapstruct :  EntityMapstruct<UserDTO,UserEntity>{


    override fun toDto(userEntity: UserEntity): UserDTO

    @InheritInverseConfiguration
    override fun toEntity(userDto: UserDTO): UserEntity

}

