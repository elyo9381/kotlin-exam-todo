package com.example.springdemo.service

import com.example.springdemo.dto.PageDTO
import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.mapper.TodoMapper
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.repository.TodoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class TodoService(
    private var todoRepository: TodoRepository,
    private var todoMapstruct: TodoMapstruct,
    private var todoMapper: TodoMapper
) {

    private val log = LoggerFactory.getLogger(TodoService::class.java)

    @Transactional
    fun createTodo(todoDto: TodoDTO): TodoDTO {

        val toEntity = todoMapstruct.toEntity(todoDto, CycleAvoidingMappingContext())
        val save = todoRepository.save(toEntity)

        return todoMapstruct.toDTO(save,CycleAvoidingMappingContext())
    }


    fun readTodo(id: Long): TodoDTO? {
        val findById = todoRepository.findById(id).get()

        log.info("findById : {}",findById)


        return todoRepository.findById(id).get()?.let { todoMapstruct.toDTO(it,CycleAvoidingMappingContext()) }

//        return todoRepository.findById(id).get()
//            ?.let { readTodoMapper.toDto(it)}
    }


    fun readAllTodo(): MutableList<TodoDTO> {
        return todoRepository.findAll()
            .map { todoMapstruct.toDTO(it,CycleAvoidingMappingContext()) }
            .toMutableList()
    }

    fun update(todoDto: TodoDTO): TodoDTO {
        val toEntity = todoMapstruct.toEntity(todoDto,CycleAvoidingMappingContext())
        val save = todoRepository.save(toEntity)
        return todoMapstruct.toDTO(save,CycleAvoidingMappingContext())
    }

    fun delete(id: Long) {
        return todoRepository.deleteById(id)
    }


    @Throws(Exception::class)
    fun SelectAllList(pageDto: PageDTO): MutableList<TodoDTO> {
        return todoMapper.SelectAllList()
    }

}