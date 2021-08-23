package com.example.springdemo.mapstruct

import com.example.springdemo.common.JsonConver
import com.example.springdemo.dto.PostDto
import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.UserDto
import com.example.springdemo.entity.PostEntity
import com.example.springdemo.entity.TodoEntity
import com.example.springdemo.entity.UserEntity
import org.apache.catalina.User
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring",uses=[PostMapstruct::class,JsonConver::class])
@Component
interface PostMapstruct : EntityMapper<PostDto,PostEntity>{

    override fun toDto(postEntity: PostEntity): PostDto

    @InheritInverseConfiguration
    override fun toEntity(postDto: PostDto): PostEntity

}
