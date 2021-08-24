package com.example.springdemo.mapstruct

import com.example.springdemo.common.JsonObjectConverter
import com.example.springdemo.dto.PostDTO
import com.example.springdemo.entity.PostEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring",uses=[PostMapstruct::class,JsonObjectConverter::class])
@Component
interface PostMapstruct : EntityMapstruct<PostDTO,PostEntity>{

    override fun toDto(postEntity: PostEntity): PostDTO

    @InheritInverseConfiguration
    override fun toEntity(postDto: PostDTO): PostEntity

}
