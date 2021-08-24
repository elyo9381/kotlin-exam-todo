package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.dto.UserDTO
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.mapstruct.UserMapstruct
import com.example.springdemo.repository.TodoRepository
import com.example.springdemo.repository.UserRepository
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

        val userData = userMapstruct.toEntity(userDto)
        entityManager.persist(userData)
        entityManager.flush()

        val findUserEntity = entityManager.find(UserEntity::class.java, userData.userno)
        val userDto2 = userMapstruct.toDto(findUserEntity)


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
//
//
        val todoData1 = todoMapstruct.toEntity(dataDto1)
        val todoData2 = todoMapstruct.toEntity(dataDto2)
        val todoData3 = todoMapstruct.toEntity(dataDto3)

//
        findUserEntity.todos.add(todoData1)
        findUserEntity.todos.add(todoData2)
        findUserEntity.todos.add(todoData3)

        val persist1 = entityManager.persist(todoData1)
        val persist2 = entityManager.persist(todoData2)
        val persist3 = entityManager.persist(todoData3)
//


//
//        val boardData = BoardEntity().apply {
//            this.boardno = null
//            this.title = "Board test ~~"
//            this.createAt = LocalDateTime.now()
//        }

//        entityManager.persist(userData)
//        boardData.user = userData
//        val saveboard = entityManager.persist(boardData)

        entityManager.flush()

        val findTodoEntity = entityManager.find(TodoEntity::class.java, persist1.todono)

//        val toDtoList = todoMapper.toDtoList(userData.todos)

//        log.info(" toDtoList :{}", toDtoList)


        val savedTodoDto = todoMapstruct.toDto(findTodoEntity)
        val savedUserDto = userMapstruct.toDto(findUserEntity)
//
        log.info(" savedTodoDto :{}", savedTodoDto)
        log.info(" savedUserDto :{}", savedUserDto)
//
//        println(savedTodoDto)
//        println(savedUserDto)


//        val saveBoard = entityManager.find(BoardEntity::class.java, saveboard.boardno)
//
//        log.info(" saveBoard :{}", saveBoard)
//
//        assertEquals(1, saveBoard.user!!.userno)
//        assertEquals("Board test ~~", saveBoard.title)
//        assertEquals("won1",userData.name)
//
//        assertNotNull(savedTodoDto.user)
//        assertEquals(1, savedTodoDto.user!!.userno)
//        assertEquals("test title won~~", savedTodoDto.title)
//        assertEquals("won1",userData.name)

    }


    @Test
    @DisplayName("boardEntity ,userEntity relation test")
    fun testBoardEntity() {

        val userData = userRepository.findById(1).get()

        val boardData = BoardEntity().apply {
            this.boardno = null
            this.title = "Board test ~~"
            this.createAt = LocalDateTime.now()
        }

        entityManager.persist(userData)
        boardData.user = userData
        val persist1 = entityManager.persist(boardData)
        entityManager.flush()


        val find = entityManager.find(BoardEntity::class.java, persist1.boardno)
        val savedUserDto = userMapstruct.toDto(userData)

        log.info(" savedTodoDto :{}", find)
        log.info(" savedUserDto :{}", savedUserDto)

        assertNotNull(find.user)
        assertEquals(1, find.user!!.userno)
        assertEquals("Board test ~~", find.title)
        assertEquals("Won5",userData.name)

    }


}