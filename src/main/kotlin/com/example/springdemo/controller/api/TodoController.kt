package com.example.springdemo.controller.api

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.readTodoDto
import com.example.springdemo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/todo")
class TodoController(private var todoService: TodoService) {


    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.createTodo(todoDto)
    }

    @GetMapping(path = ["/{id}"])
    fun findOne(@PathVariable() id: Long?): ResponseEntity<Any?> {

        val findTodo = id?.let {
            val readTodo = todoService.readTodo(it)
            readTodo
        }
        return ResponseEntity.status(HttpStatus.OK).body(findTodo)

    }
    // R
    @GetMapping(path = [""])
    fun readAll(): MutableList<readTodoDto> {
        return todoService.readAllTodo()
    }


    @PutMapping(path = [""]) // create = 201 , update = 200
    fun update(@Valid @RequestBody todoDto: TodoDto): ResponseEntity<Any?> {


        val update = todoService.update((todoDto))

        update.id?.let {
            return ResponseEntity.status(HttpStatus.OK).body(update)
        } ?: kotlin.run {
            return ResponseEntity.status(HttpStatus.CREATED).body(update)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun delete(@PathVariable id:Long): ResponseEntity<Any> {
        if(todoService.delete(id) == null){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }
}