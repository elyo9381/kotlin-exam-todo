package com.example.springdemo.service

import com.example.springdemo.dto.TodoPageDto
import com.example.springdemo.dto.TodoDto
import com.example.springdemo.mapper.TodoSqlMapper
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.TodoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TodoService(
    private var todoRepository: TodoRepository,
    private var todoMapper: TodoMapper,
    private var todoSqlMapper: TodoSqlMapper
) {

    private val log = LoggerFactory.getLogger(TodoService::class.java)

    @Transactional
    fun createTodo(todoDto: TodoDto): TodoDto {

        val toEntity = todoMapper.toEntity(todoDto)
        val save = todoRepository.save(toEntity)

        return todoMapper.toDto(save)
    }

    fun readTodo(id: Long): TodoDto? {
        val findById = todoRepository.findById(id).get()

        log.info("findById : {}",findById)


        return todoRepository.findById(id).get()?.let { todoMapper.toDto(it) }

//        return todoRepository.findById(id).get()
//            ?.let { readTodoMapper.toDto(it)}
    }


    fun readAllTodo(): MutableList<TodoDto> {
        return todoRepository.findAll()
            .map { todoMapper.toDto(it) }
            .toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto {
        val toEntity = todoMapper.toEntity(todoDto)
        val save = todoRepository.save(toEntity)
        return todoMapper.toDto(save)
    }

    fun delete(id: Long): Unit? {
        return todoRepository.deleteById(id)
    }


    @Throws(Exception::class)
    fun SelectAllList(todoPageDto: TodoPageDto): MutableList<TodoDto> {
        return todoSqlMapper.SelectAllList()
    }

}