package com.example.springdemo.service

import com.example.springdemo.dto.UserDto
import com.example.springdemo.mapstruct.UserMapper
import com.example.springdemo.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private var userRepository: UserRepository,
    private var userMapper: UserMapper
) {

    fun userSave(userDto: UserDto): UserDto {
        val toEntity = userMapper.toEntity(userDto)
        val save = userRepository.save(toEntity)
        return userMapper.toDto(save)
    }

    fun getUsers(): MutableList<UserDto> {
        return userRepository.findAll()
            .map { userMapper.toDto(it) }
            .toMutableList()
    }

}