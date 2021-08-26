package com.example.springdemo.entity

import com.example.springdemo.config.GsonConfig
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

//@ExtendWith()
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class EntityRelationTest @Autowired constructor(
    @Autowired private var todoRepository: TodoRepository,
    @Autowired private val userRepository: UserRepository
) {

    val todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)
    val userMapstruct = Mappers.getMapper(UserMapstruct::class.java)

    private val gson = Gson()

    @Autowired
    private lateinit var entityManager: TestEntityManager

    private val log = LoggerFactory.getLogger(EntityRelationTest::class.java)

    @Test
    @DisplayName("todoEntity ,userEntity relation test")
    fun testTodoSave() {

        val userDto = UserDTO().apply {
            this.name = "won1"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        val userData = userMapstruct.toEntity(userDto, CycleAvoidingMappingContext())
        entityManager.persist(userData)
        entityManager.flush()

        val findUserEntity = entityManager.find(UserEntity::class.java, userData.userno)
        val userDto2 = userMapstruct.toDTO(findUserEntity, CycleAvoidingMappingContext())


        val dataDto1 = TodoDTO().apply {
            this.title = "test title won~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2
        }


        val dataDto2 = TodoDTO().apply {
            this.title = "test won2~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2

        }


        val dataDto3 = TodoDTO().apply {
            this.title = "test won3~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }
        val todoData1 = todoMapstruct.toEntity(dataDto1, CycleAvoidingMappingContext())
        val todoData2 = todoMapstruct.toEntity(dataDto2, CycleAvoidingMappingContext())
        val todoData3 = todoMapstruct.toEntity(dataDto3, CycleAvoidingMappingContext())

        findUserEntity.todos.add(todoData1)
        findUserEntity.todos.add(todoData2)
        findUserEntity.todos.add(todoData3)


        val jsonObject1: String = """
            {
                "todono": 1,
                "title": "test title won~~",
                "description": "base test description",
                "schedule": "2021-08-26 06:06:00"
            }"""
        val jsonObject2: String = """{
            "todono": 2,
            "title": "test won2~~",
            "description": "base test description",
            "schedule": "2021-08-26 06:06:00"
        }"""
        val jsonObject3: String = """{
            "todono": 3,
            "title": "test won3~~",
            "description": "base test description",
            "schedule": "2021-08-26 06:06:00",
            "createdAt": "2021-08-26 06:06:00",
            "updatedAt": "2021-08-26 06:06:00"
        }
        """

        val fromJson1 = gson.fromJson(jsonObject1, JsonObject::class.java)
        val fromJson2 = gson.fromJson(jsonObject2, JsonObject::class.java)
        val fromJson3 = gson.fromJson(jsonObject3, JsonObject::class.java)

        val jsonArray = JsonArray().apply {
            add(fromJson1)
            add(fromJson2)
            add(fromJson3)
        }

        findUserEntity.todolist = jsonArray

        val persist1 = entityManager.persist(todoData1)
        val persist2 = entityManager.persist(todoData2)
        val persist3 = entityManager.persist(todoData3)

        entityManager.persist(findUserEntity)

        entityManager.flush()
//        entityManager.clear()

//
//        val findTodoEntity = entityManager.find(TodoEntity::class.java, persist1.todono)
//        val UserFindAndSave = entityManager.find(UserEntity::class.java, findUserEntity.userno)
//
//
//        val savedTodoDto = todoMapstruct.toDTO(findTodoEntity, CycleAvoidingMappingContext())
//        val savedUserDto = userMapstruct.toDTO(UserFindAndSave, CycleAvoidingMappingContext())
//        log.info(" savedTodoDto :{}", savedTodoDto)
//        log.info(" savedUserDto :{}", savedUserDto)

    }


}