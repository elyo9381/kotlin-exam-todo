package com.example.springdemo.mapstruct

import com.example.springdemo.dto.UserDTO
import com.example.springdemo.entity.UserEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
@Component
interface UserMapstruct {


    fun toDTO(userEntity: UserEntity,@Context context: CycleAvoidingMappingContext): UserDTO
    @InheritInverseConfiguration
    fun toEntity(userDto: UserDTO, @Context context: CycleAvoidingMappingContext): UserEntity
}

