package com.example.springdemo.service

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.readDto
import com.example.springdemo.dto.readTodoDto
import com.example.springdemo.entity.Todo
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private var todoRepository: TodoRepository,
    private var todoMapper: TodoMapper
) {

    fun createTodo(todoDto: TodoDto): TodoDto {
        return todoDto.let {
            todoMapper.toTodo(it)
        }.let {
            todoRepository.save(it)
        }?.let {
            todoMapper.toTodoDto(it)
        }
    }

    fun readTodo(id: Long): readTodoDto? {
        return todoRepository.findById(id).get()
            ?.let { readTodoDto().readDto(it) }
    }

    fun readAllTodo(): MutableList<readTodoDto> {
        return todoRepository.findAll()
            .map { readTodoDto().readDto(it) }
            .toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepository.save(it)
        }.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun delete(id: Long): Unit? {
        return todoRepository.deleteById(id)
    }


}