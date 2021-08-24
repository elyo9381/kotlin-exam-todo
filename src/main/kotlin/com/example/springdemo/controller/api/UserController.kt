package com.example.springdemo.controller.api

import com.example.springdemo.dto.*
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.UserRepository
import com.example.springdemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user")
class UserController(
    private var userService: UserService,
    private var userMapstruct: UserMapstruct,
    private var userRepository: UserRepository
) {


    @PostMapping(path = [""])
    fun create(@RequestBody userDto: UserDTO): ResponseEntity<Any?> {
        val createUser = userService.userSave(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser)
    }

    @GetMapping("/mybatis-list")
    fun users() : ResponseEntity<Any>{
        val users = userService.users()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/list")
    fun AllListView(): ResponseEntity<Any> {
        val readAllUser = userService.userLists()
        return ResponseEntity.ok(readAllUser)
    }

//    @GetMapping(path = ["/{id}"])
//    fun findOne(@PathVariable(required = false) id: Long): ResponseEntity<Any?> {
//        val findById: PostEntity = postRepository.findById(id).get()
//        val toDto = postMapstruct.toDto(findById)
//        return ResponseEntity.ok(toDto)
//    }

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