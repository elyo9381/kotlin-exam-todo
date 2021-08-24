package com.example.springdemo.mapper

import com.example.springdemo.dto.PageDTO
import com.example.springdemo.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Repository
@Mapper
interface UserMapper {

    @Throws(Exception::class)
    fun Users(): MutableList<UserDTO>

    //Paging
    @Throws(Exception::class)
    fun Users(pageDto: PageDTO): MutableList<UserDTO>

    //count
    @Throws(Exception::class)
    fun testTableCount(search: PageDTO): Long
}