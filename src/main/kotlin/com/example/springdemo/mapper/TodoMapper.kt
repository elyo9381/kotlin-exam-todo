package com.example.springdemo.mapper

import com.example.springdemo.dto.PageDTO
import com.example.springdemo.dto.TodoDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Repository
@Mapper
interface TodoMapper {

    //select * from Test_Table
    @Throws(Exception::class)
    fun SelectAllList(): MutableList<TodoDTO>

    //Paging
    @Throws(Exception::class)
    fun SelectAllList(pageDto: PageDTO): MutableList<TodoDTO>

    //count
    @Throws(Exception::class)
    fun testTableCount(search: PageDTO): Long
}