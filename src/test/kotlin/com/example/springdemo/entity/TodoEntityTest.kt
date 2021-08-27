package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDTO
import com.example.springdemo.mapstruct.CycleAvoidingMappingContext
import com.example.springdemo.mapstruct.TodoMapstruct
import com.example.springdemo.repository.TodoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDateTime

//@ExtendWith()
@DataJpaTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class TodoEntityTest @Autowired constructor(@Autowired private var todoRepository: TodoRepository){

    val todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)

    var baseId = 31L

    @Test
    @DisplayName("todoEntity save test")
    fun testTodoSave() {
        val testDto = TodoDTO().apply {
            this.title = "new save"
            this.description = "hihihi~~~"
            this.schedule = LocalDateTime.now()
        }

        val testTodo = todoMapstruct.toEntity(testDto,CycleAvoidingMappingContext())

        val saveTodo= todoRepository.save(testTodo)

        assertEquals( "new save", saveTodo.title )
    }

    @Test
    @DisplayName("todoEntity findOne test")
    fun testReadTodo(){
        val todono = baseId
        val findById = todoRepository.findById(todono)
        assertEquals("new save",findById.get().title)
    }


    @Test
    @DisplayName("todoEntity update test, dirtyChecking")
    fun testUpdate(){
        val todono = baseId
        val findById = todoRepository.findById(todono).get()

        findById.apply {
            this.title = "update kim"
        }

        val save = todoRepository.save(findById)

        assertEquals(baseId,save.todono)
        assertEquals("update kim",save.title)
    }

    @Test
    @DisplayName("todoEntity delete test")
    fun testDelete(){
        val todono = baseId
        todoRepository.deleteById(todono)
    }
}