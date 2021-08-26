package com.example.springdemo.mapstruct

import com.example.springdemo.common.JsonObjectConverter
import com.example.springdemo.dto.PostDTO
import com.example.springdemo.entity.PostEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring",uses=[JsonObjectConverter::class])
@Component
interface PostMapstruct {


    fun toDTO(postEntity: PostEntity): PostDTO

    @InheritInverseConfiguration
     fun toEntity(postDto: PostDTO): PostEntity

}
