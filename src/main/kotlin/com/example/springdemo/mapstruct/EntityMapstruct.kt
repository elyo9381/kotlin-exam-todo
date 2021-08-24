package com.example.springdemo.mapstruct


interface EntityMapstruct <D,E> {
    fun toEntity(dto: D): E
    fun toDto(entity: E): D

    fun toEntityList(list:MutableList<D>):MutableList<E>
    fun toDtoList(list:MutableList<E>):MutableList<D>

}