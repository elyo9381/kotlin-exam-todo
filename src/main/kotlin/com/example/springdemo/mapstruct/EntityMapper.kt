package com.example.springdemo.mapstruct

import com.example.springdemo.entity.TodoEntity

interface EntityMapper <D,E> {
    fun toEntity(dto: D): E


    fun toDto(entity: E): D

    fun toEntityList(list:MutableList<D>):MutableList<E>
    fun toDTOList(list:MutableList<E>):MutableList<D>

}