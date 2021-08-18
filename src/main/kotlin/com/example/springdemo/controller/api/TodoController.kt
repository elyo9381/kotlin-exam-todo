package com.example.springdemo.controller.api

import com.example.springdemo.dto.*
import com.example.springdemo.mapper.TodoSqlMapper
import com.example.springdemo.service.TodoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/todo")
class TodoController(
    private var todoService: TodoService,
    var memberSqlMapper: TodoSqlMapper
) {

    private val log = LoggerFactory.getLogger(TodoController::class.java)

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): ResponseEntity<Any?> {

        val createTodo = todoService.createTodo(todoDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(createTodo)
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
    fun readAll(): MutableList<ReadTodoDto> {
        return todoService.readAllTodo()
    }


//    @GetMapping("/list")
//    fun findAllBoard(@RequestParam page_number: Int?, @RequestParam page_size: Int?):  ResponseEntity<Any?> {
//
//        log.info("page_number = :{}",page_number)
//        log.info("page_size = :{}",page_size)
//
//        val readTodoPage = todoService.readTodoPage(page_number, page_size)
//
//        return ResponseEntity.status(HttpStatus.OK).body(readTodoPage)
//    }


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
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (todoService.delete(id) == null) {
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }

//    @PostMapping(value = ["/list"])
//    @Throws(Exception::class)
//    fun AllListView(
//        @RequestParam(value = "currentPage", required = false, defaultValue = "1") currentPage: Int,
//        @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") cntPerPage: Int,
//        @RequestParam(value = "pageSize", required = false, defaultValue = "10") pageSize: Int,
//        @RequestParam(value = "keyword", required = false) keyword : String): ResponseEntity<Any> {
//
//        val listCnt: Int = todoService.testTableCount()
//        val pageDto = PageDto(currentPage, cntPerPage, pageSize)
//
//        pageDto.addTotalRecordCount(listCnt)
//
//        val page = pageReadDto().apply {
//            this.pageList = todoService.SelectAllList(pageDto)
//            this.pageInfo = pageDto
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(page)
//    }
    @PostMapping(value = ["/list"])
    @Throws(Exception::class)
    fun AllListView(@RequestBody requestPageDto: RequestPageDto): ResponseEntity<Any> {


        log.info("requestPageDto : {}" , requestPageDto)
        val listCnt: Int = todoService.testTableCount(requestPageDto.keyword)
        val pageDto = PageDto(requestPageDto.currentPage, requestPageDto.cntPerPage, requestPageDto.pageSize,requestPageDto.keyword)
        pageDto.addTotalRecordCount(listCnt)


        log.info("pageDto : {}" ,pageDto )
        val page = pageReadDto().apply {
            this.pageList = todoService.SelectAllList(pageDto)
            this.pageInfo = pageDto
        }
        return ResponseEntity.status(HttpStatus.OK).body(page)
    }

}