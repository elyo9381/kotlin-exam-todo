package com.example.springdemo.mapper

import com.example.springdemo.dto.PageDto
import com.example.springdemo.dto.ReadTodoDto
import com.example.springdemo.utils.Pagination
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Repository
@Mapper
interface TodoSqlMapper {

    fun findPage(offset: Int?, page_size: Int?): MutableList<ReadTodoDto?>?

    //select * from Test_Table
    @Throws(Exception::class)
    fun SelectAllList(): MutableList<Map<String?, Any?>?>?

    //Paging
    @Throws(Exception::class)
    fun SelectAllList(pageDto: PageDto?): MutableList<Map<String?, Any?>?>?

    //count
    @Throws(Exception::class)
    fun testTableCount(keyword:String): Int

    @Throws(Exception::class)
    fun testTableCount(): Int
}