package com.example.springdemo.controller.api

import com.example.springdemo.dto.*
import com.example.springdemo.mapper.TodoMapper
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.service.TodoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/todo")
class TodoController(
    private var todoService: TodoService,
    var todoMapper: TodoMapper
) {
    @Autowired
    private lateinit var todoMapstruct: TodoMapstruct

    @Autowired
    private lateinit var todoRepository: TodoRepository

    private val log = LoggerFactory.getLogger(TodoController::class.java)

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDTO): ResponseEntity<Any?> {
//        val createTodoDto = todoService.createTodo(todoDto)

        val toEntity = todoMapstruct.toEntity(todoDto)
        val save = todoRepository.save(toEntity)
        val toDTO = todoMapstruct.toDto(save)

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO)
    }

    @GetMapping(path = ["/{id}"])
    fun findOne(@PathVariable(required = false) id: Long): ResponseEntity<Any?> {

        id.let {
            val readTodo = todoService.readTodo(it)
            return ResponseEntity.ok(readTodo)
        }
        // 없을때 예외처리 해야함
//            ?: kotlin.run {
//            return ResponseEntity.ok(hashMapOf("mode" to false, "msg" to "아이디가 없습니다."))
//        }

    }


    @PutMapping(path = [""]) // create = 201 , update = 200
    fun update(@Valid @RequestBody todoDto: TodoDTO, binding: BindingResult): ResponseEntity<Any?> {

        if (binding.hasErrors()) {

        }
//      val update = todoService.update(todoDto)

        val toEntity = todoMapstruct.toEntity(todoDto)
        val save = todoRepository.save(toEntity)
        val toDto = todoMapstruct.toDto(save)

        return ResponseEntity.ok(toDto)
    }

    @DeleteMapping(path = ["/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (todoService.delete(id) == null) {
            return ResponseEntity.ok(hashMapOf("mode" to false, "msg" to "아이디가 없습니다."))
        }
        return ResponseEntity.ok().build()
    }

    @PostMapping("/list")
    @ResponseBody
    fun AllListView(@RequestBody pageDto: PageDTO): ResponseEntity<Any> {
        log.info("requestPageDto : {}", pageDto)

        todoService.SelectAllList(pageDto)

        val pageRequest = PageRequest.of(pageDto.startPage, pageDto.limit)
        val list = todoMapper.SelectAllList(pageDto)
        val count = todoMapper.testTableCount(pageDto)

        return ResponseEntity.ok(PageImpl(list, pageRequest, count))
    }

}