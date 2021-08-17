package com.example.springdemo.controller.api

import com.example.springdemo.dto.TodoDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest
@AutoConfigureMockMvc
internal class TodoControllerTest{



    @Autowired
    lateinit var mockMvc: MockMvc


    @Test
    fun postTest(){

        val creatTodo = TodoDto().apply {
            this.id = null
            this.titleName = "title"
            this.description = "~~~blabla"
            this.schedule = "2021-08-17 12:48:00"
            this.createdAt = null
            this.updatedAt = null
        }

        val json = jacksonObjectMapper().writeValueAsString(creatTodo)
        println(json)


        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/todo")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isCreated
        )
    }
}