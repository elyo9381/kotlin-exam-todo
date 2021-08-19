package com.example.springdemo.mapper

import com.example.springdemo.dto.TodoPageDto
import com.example.springdemo.dto.TodoDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Repository
@Mapper
interface TodoSqlMapper {

    //select * from Test_Table
    @Throws(Exception::class)
    fun SelectAllList(): MutableList<TodoDto>

    //Paging
    @Throws(Exception::class)
    fun SelectAllList(pageDto: TodoPageDto): MutableList<TodoDto>

    //count
    @Throws(Exception::class)
    fun testTableCount(search: TodoPageDto): Long
}