package com.example.springdemo.service

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.readDto
import com.example.springdemo.dto.ReadTodoDto
import com.example.springdemo.entity.Todo
import com.example.springdemo.mapstruct.ReadTodoMapper
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private var todoRepository: TodoRepository,
    private var todoMapper: TodoMapper,
    private var readTodoMapper: ReadTodoMapper
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


}