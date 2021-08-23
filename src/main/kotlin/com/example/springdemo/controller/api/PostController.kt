package com.example.springdemo.controller.api

import com.example.springdemo.dto.*
import com.example.springdemo.entity.PostEntity
import com.example.springdemo.mapper.TodoSqlMapper
import com.example.springdemo.mapstruct.PostMapstruct
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.PostRepository
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
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/post")
class PostController(
    private var todoService: TodoService,
    var todoSqlMapper: TodoSqlMapper
) {
    @Autowired
    private lateinit var postMapstruct: PostMapstruct

    @Autowired
    private lateinit var postRepository: PostRepository

    private val log = LoggerFactory.getLogger(PostController::class.java)

    @PostMapping(path = [""])
    fun create(@RequestBody postDto: PostDto): ResponseEntity<Any?> {
//        val createTodoDto = todoService.createTodo(todoDto)

        val toEntity = postMapstruct.toEntity(postDto)
        val save = postRepository.save(toEntity)
        val toDTO = postMapstruct.toDto(save)

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO)
    }

    @GetMapping(path = ["/{id}"])
    fun findOne(@PathVariable(required = false) id: Long): ResponseEntity<Any?> {

        val findById: PostEntity = postRepository.findById(id).get()


        val toDto = postMapstruct.toDto(findById)
        // 뭐야 noSuchElementException난다.
//        val get = postRepository.findById(id).get()


//        log.info("findnullcheck : {} " , get)
//        if(findById.isEmpty){
//            return ResponseEntity.ok(hashMapOf("id" to "${id} 가 없습니다."))
//        }



        return ResponseEntity.ok(toDto)

    }

//    @PutMapping(path = [""]) // create = 201 , update = 200
//    fun update(@Valid @RequestBody todoDto: TodoDto, binding: BindingResult): ResponseEntity<Any?> {
//
//        if (binding.hasErrors()) {
//
//        }
////      val update = todoService.update(todoDto)
//
//        val toEntity = todoMapper.toEntity(todoDto)
//        val save = todoRepository.save(toEntity)
//        val toDTO = todoMapper.toDto(save)
//
//        return ResponseEntity.ok(todoDto)
//    }
//
//    @DeleteMapping(path = ["/{id}"])
//    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
//        if (todoService.delete(id) == null) {
//            return ResponseEntity.ok(hashMapOf("mode" to false, "msg" to "아이디가 없습니다."))
//        }
//        return ResponseEntity.ok().build()
//    }
//
//    @PostMapping("/list")
//    @ResponseBody
//    fun AllListView(@RequestBody todoPageDto: TodoPageDto): ResponseEntity<Any> {
//        log.info("requestPageDto : {}", todoPageDto)
//
//        todoService.SelectAllList(todoPageDto)
//
//        val pageRequest = PageRequest.of(todoPageDto.startPage, todoPageDto.limit)
//        val list = todoSqlMapper.SelectAllList(todoPageDto)
//        val count = todoSqlMapper.testTableCount(todoPageDto)
//
//        return ResponseEntity.ok(PageImpl(list, pageRequest, count))
//    }

}