package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.dto.UserDto
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.mapstruct.UserMapper
import com.example.springdemo.repository.TodoRepository
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
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class EntityRelationTest @Autowired constructor(@Autowired private var todoRepository: TodoRepository) {

    val todoMapper = Mappers.getMapper(TodoMapper::class.java)
    val userMapper = Mappers.getMapper(UserMapper::class.java)
    @Autowired
    private lateinit var entityManager: TestEntityManager

    private val log = LoggerFactory.getLogger(EntityRelationTest::class.java)

    @Test
    @DisplayName("todoEntity ,userEntity relation test")
    fun testTodoSave() {

        val userDto = UserDto().apply {
            this.name = "won1"
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        val userData = userMapper.toEntity(userDto)
        entityManager.persist(userData)
        entityManager.flush()

        val findUserEntity = entityManager.find(UserEntity::class.java, userData.userno)
        val userDto2 = userMapper.toDto(findUserEntity)


        val dataDto1 = TodoDto().apply {
            this.title = "test title won~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2
        }


        val dataDto2 = TodoDto().apply {
            this.title = "test won2~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2

        }


        val dataDto3 = TodoDto().apply {
            this.title = "test won3~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
            this.user = userDto2
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }
//
//
        val todoData1 = todoMapper.toEntity(dataDto1)
        val todoData2 = todoMapper.toEntity(dataDto2)
        val todoData3 = todoMapper.toEntity(dataDto3)

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


        val savedTodoDto = todoMapper.toDto(findTodoEntity)
        val savedUserDto = userMapper.toDto(findUserEntity)
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

//
//    @Test
//    @DisplayName("boardEntity ,userEntity relation test")
//    fun testBoardEntity() {
//
////        val userData = entityManager.find(UserEntity::class.java, "")
//
//
//        val boardData = BoardEntity().apply {
//            this.boardno = null
//            this.title = "Board test ~~"
//            this.createAt = LocalDateTime.now()
//        }
//
//        entityManager.persist(userData)
//        boardData.user = userData
//        val persist1 = entityManager.persist(boardData)
//        entityManager.flush()
//
//
//        val find = entityManager.find(BoardEntity::class.java, persist1.boardno)
//        val savedUserDto = userMapper.toDto(userData)
//
//        log.info(" savedTodoDto :{}", find)
//        log.info(" savedUserDto :{}", savedUserDto)
//
//        assertNotNull(find.user)
//        assertEquals(2, find.user!!.userno)
//        assertEquals("Board test ~~", find.title)
//        assertEquals("won2",userData.name)
//
//    }


}