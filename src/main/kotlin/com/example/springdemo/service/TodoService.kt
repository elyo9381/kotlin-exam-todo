package com.example.springdemo.service

import com.example.springdemo.dto.PageDto
import com.example.springdemo.dto.ReadTodoDto
import com.example.springdemo.dto.TodoDto
import com.example.springdemo.mapper.TodoSqlMapper
import com.example.springdemo.mapstruct.ReadTodoMapper
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.utils.Pagination
import org.springframework.stereotype.Service


@Service
class TodoService(
    private var todoRepository: TodoRepository,
    private var todoMapper: TodoMapper,
    private var readTodoMapper: ReadTodoMapper,
    private var todoSqlMapper: TodoSqlMapper
) {

    fun createTodo(todoDto: TodoDto): TodoDto {
        return todoDto.let {
            todoMapper.toTodo(it)
        }.let {
            todoRepository.save(it)
        }.let {
            todoMapper.toTodoDto(it)
        }
    }

    fun readTodo(id: Long): ReadTodoDto? {
        return todoRepository.findById(id).get()
            ?.let { readTodoMapper.toDto(it)}
    }


    fun readTodoPage(page_number: Int?, page_size: Int?): MutableList<ReadTodoDto?>? {
        var offset: Int? = null
        if (page_number != null && page_size != null) {
            offset = (page_number - 1) * page_size
        }
        return todoSqlMapper.findPage(offset, page_size)
    }

    fun readAllTodo(): MutableList<ReadTodoDto> {
        return todoRepository.findAll()
            .map { readTodoMapper.toDto(it) }
            .toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto {
        return todoDto.let {
            todoMapper.toTodo(it)
        }.let {
            todoRepository.save(it)
        }.let {
            todoMapper.toTodoDto(it)
        }
    }

    fun delete(id: Long): Unit? {
        return todoRepository.deleteById(id)
    }


    @Throws(Exception::class)
    fun SelectAllList(): MutableList<Map<String?, Any?>?>? {
        return todoSqlMapper.SelectAllList()
    }

    //Paging
    @Throws(Exception::class)
    fun SelectAllList(pageDto: PageDto?): MutableList<Map<String?, Any?>?>? {
        return todoSqlMapper.SelectAllList(pageDto)
    }

    //count
    @Throws(Exception::class)
    fun testTableCount(keyword: String): Int {
        return todoSqlMapper.testTableCount(keyword)
    }


}