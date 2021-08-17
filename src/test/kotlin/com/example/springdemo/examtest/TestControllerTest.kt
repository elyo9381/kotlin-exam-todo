//package com.example.springdemo.examtest
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.test.web.client.getForEntity
//import org.springframework.http.HttpStatus
//
//@ExtendWith
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class TestControllerTest(@Autowired val restTemplate: TestRestTemplate){
//
//    @Test
//    fun `Assert blogpage body content, content and status code`(){
//        val entity = restTemplate.getForEntity<String>("/blog")
//        assertEquals(HttpStatus.OK,entity.statusCode)
//        assertThat(entity.body).contains("blog")
//    }
//
//}