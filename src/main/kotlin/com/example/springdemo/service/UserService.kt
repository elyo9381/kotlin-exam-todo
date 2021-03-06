package com.example.springdemo.service

import com.example.springdemo.dto.UserDTO
import com.example.springdemo.mapper.UserMapper
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private var userRepository: UserRepository,
    private var userMapstruct: UserMapstruct,
    private var userMapper: UserMapper
) {

    fun userSave(userDto: UserDTO): UserDTO {
        val toEntity = userMapstruct.toEntity(userDto, CycleAvoidingMappingContext())
        val save = userRepository.save(toEntity)
        return userMapstruct.toDTO(save,CycleAvoidingMappingContext())
    }

    @Throws(Exception::class)
    fun users() : MutableList<UserDTO> {
        return userMapper.Users()
    }

    fun userLists(): MutableList<UserDTO> {
        return userRepository.findAll()
            .map { userMapstruct.toDTO(it,CycleAvoidingMappingContext()) }
            .toMutableList()
    }

}