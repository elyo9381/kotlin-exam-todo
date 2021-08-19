//package com.example.springdemo.service
//
//import com.example.springdemo.dto.TodoDto
//import com.example.springdemo.mapstruct.ReadTodoMapper
//import com.example.springdemo.mapstruct.TodoMapper
//import com.example.springdemo.mapper.TodoSqlMapper
//import com.example.springdemo.repository.TodoRepository
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mapstruct.factory.Mappers
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.TestConstructor
//
//
//@SpringBootTest
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//internal class TodoEntityServiceTest @Autowired constructor(
//    @Autowired var todoService: TodoService,
//    @Autowired var todoRepository: TodoRepository
//) {
//
//    val todoMapper = Mappers.getMapper(TodoMapper::class.java)
//
//
//    @Test
//    internal fun testTodoCount() {
//
//        val newTodo = TodoDto().apply {
//            this.titleName = "wonyoel3"
//            this.description = "hihihi~~~"
//            this.schedule = "2021-08-17 10:48:09"
//        }
//
//
//        val createTodoService = todoService.createTodo(newTodo)
//        println(createTodoService)
//
//        val findTodo = todoRepository.findByTitle(createTodoService.titleName)
//        println(findTodo)
//
//        assertNotNull(createTodoService)
//        assertNotNull(findTodo)
//        assertEquals(findTodo?.title, createTodoService.titleName)
//
//    }
//}