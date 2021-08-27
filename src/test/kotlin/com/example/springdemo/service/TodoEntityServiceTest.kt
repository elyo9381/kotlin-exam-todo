package com.example.springdemo.service

import com.example.springdemo.dto.TodoDTO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime


@SpringBootTest
internal class TodoEntityServiceTest @Autowired constructor(
    @Autowired var todoService: TodoService,
) {


    @Test
    @DisplayName("todoService save")
    fun test1() {

        val newTodo = TodoDTO().apply {
            this.title = "wonyoel3"
            this.description = "hihihi~~~"
            this.schedule = LocalDateTime.now()
        }

        val createTodoService = todoService.createTodo(newTodo)

        assertNotNull(createTodoService)
        assertEquals("title", createTodoService.title)


    }

    @Test
    @DisplayName("todoService read")
    fun test2() {

        val readTodoData = todoService.readTodo(6)
        println(readTodoData)

        assertNotNull(readTodoData)
        assertEquals("test won3~~", readTodoData?.title)
        assertEquals(3, readTodoData?.user?.todos?.size )


    }

    @Test
    @DisplayName("todoService findAll")
    fun test3() {

        val readAll = todoService.readAllTodo()
        println(readAll)
        assertNotNull(readAll)
        println(readAll.size)


    }

    @Test
    @DisplayName("todoService delete")
    fun test4() {


        val delete = todoService.delete(34)
        println(delete)
//        assertEquals(null, delete)

    }
}