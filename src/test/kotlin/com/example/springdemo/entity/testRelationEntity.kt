package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.dto.UserDTO
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.annotation.Rollback
import java.time.LocalDateTime

//@ExtendWith()
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class testRelationEntity @Autowired constructor(
    @Autowired private var todoRepository: TodoRepository,
    @Autowired private val userRepository: UserRepository
) {

    val todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)
    val userMapstruct = Mappers.getMapper(UserMapstruct::class.java)

    private val gson = Gson()

    @Autowired
    private lateinit var entityManager: TestEntityManager

    private val log = LoggerFactory.getLogger(testRelationEntity::class.java)

    @Test
    @DisplayName("todoEntity ,userEntity relation test &  jsonArray 테스트 ")
    fun testTodoSave() {

        // 유저생성 및 저장
        val userDTO = UserDTO().apply {
            this.name = "won1"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
            this.info = crerateInfoJsonArray()
        }

        val userData = userMapstruct.toEntity(userDTO, CycleAvoidingMappingContext())
        val savedUser = userRepository.save(userData)


        val findUserDTO = userMapstruct.toDTO(savedUser, CycleAvoidingMappingContext())

        // todo생성 및 저장  -> 유저의 todo로 저장
        val dataDto1 = TodoDTO().apply {
            this.title = "test title won~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = findUserDTO
        }
        val dataDto2 = TodoDTO().apply {
            this.title = "test won2~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = findUserDTO

        }
        val dataDto3 = TodoDTO().apply {
            this.title = "test won3~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = findUserDTO
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        val convertFindUser = userMapstruct.toEntity(findUserDTO, CycleAvoidingMappingContext())

        // todo 저장을 위한 변환
        val todoData1 = todoMapstruct.toEntity(dataDto1, CycleAvoidingMappingContext())
        val todoData2 = todoMapstruct.toEntity(dataDto2, CycleAvoidingMappingContext())
        val todoData3 = todoMapstruct.toEntity(dataDto3, CycleAvoidingMappingContext())


        convertFindUser.todos.add(todoData1)
        convertFindUser.todos.add(todoData2)
        convertFindUser.todos.add(todoData3)

        // test json 어레이
        val jsonArray = crerateTodoListJsonArray()
        convertFindUser.todolist = jsonArray

        todoRepository.save(todoData1)
        todoRepository.save(todoData2)
        todoRepository.save(todoData3)

        val saveUser = userRepository.save(convertFindUser)

        val savedUserDto = userMapstruct.toDTO(saveUser, CycleAvoidingMappingContext())


        assertNotNull(savedUserDto)
        assertEquals("won1", saveUser.name)
        assertEquals(crerateInfoJsonArray().toString(), saveUser.info.toString())
        assertEquals(3, saveUser.todos.size)
        assertEquals(jsonArray, saveUser.todolist)

    }

    private fun crerateTodoListJsonArray(): JsonArray {
        val jsonObject1: String = """
                {
                    "todono": 1,
                    "title": "test title won~~",
                    "description": "base test description",
                    "schedule": "2021-08-26 06:06:00"
                }"""
        val jsonObject2: String = """
                {
                    "todono": 2,
                    "title": "test won2~~",
                    "description": "base test description",
                    "schedule": "2021-08-26 06:06:00"
                }
                """
        val jsonObject3: String = """
                {
                    "todono": 3,
                    "title": "test won3~~",
                    "description": "base test description",
                    "schedule": "2021-08-26 06:06:00",
                    "createdAt": "2021-08-26 06:06:00",
                    "updatedAt": "2021-08-26 06:06:00"
                }
                """

        val jsonArray = JsonArray().apply {
            add(gson.fromJson(jsonObject1, JsonObject::class.java))
            add(gson.fromJson(jsonObject2, JsonObject::class.java))
            add(gson.fromJson(jsonObject3, JsonObject::class.java))
        }
        return jsonArray
    }


    private fun crerateInfoJsonArray(): JsonArray {
        val jsonObject1: String = """
             {
                "city" : "gwangju",
                "state" : "north",
                "zipcode" : 4
             }"""
        val jsonObject2: String = """
            {
                "city" : "gwangju",
                "state" : "south",
                "zipcode" : 3
             }"""
        val jsonObject3: String = """
            {
                "city" : "gwangju",
                "state" : "west",
                "zipcode" : 2
            }
            """
        val jsonObject4: String = """
            {
                "city" : "gwangju",
                "state" : "east",
                "zipcode" : 1
            }
            """

        val jsonArray = JsonArray().apply {
            add(gson.fromJson(jsonObject1, JsonObject::class.java))
            add(gson.fromJson(jsonObject2, JsonObject::class.java))
            add(gson.fromJson(jsonObject3, JsonObject::class.java))
            add(gson.fromJson(jsonObject4, JsonObject::class.java))
        }
        return jsonArray
    }


}