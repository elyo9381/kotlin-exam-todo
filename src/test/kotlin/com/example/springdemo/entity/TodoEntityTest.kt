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
import java.time.LocalDateTime

//@ExtendWith()
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class TodoEntityTest @Autowired constructor(@Autowired private var todoRepository: TodoRepository){

    val todoMapstruct = Mappers.getMapper(TodoMapstruct::class.java)


    @BeforeEach
    fun before(){
        val dataDto1 = TodoDTO().apply {
            this.title = "test title won~~"
            this.description = "base test description"
            this.schedule = LocalDateTime.now()
        }

        val dataTodo = todoMapstruct.toEntity(dataDto1, CycleAvoidingMappingContext())
        val save = todoRepository.save(dataTodo)

        val findById = todoRepository.findById(save.todono!!)
        assertEquals( save.todono , findById.get().todono)

    }

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
        val todono : Long = 1
        val findById = todoRepository.findById(todono)
        assertEquals("test title won~~",findById.get().title)
    }


    @Test
    @DisplayName("todoEntity update test, dirtyChecking")
    fun testUpdate(){
        val findById = todoRepository.findById(1).get()

        findById.apply {
            this.title = "update kim"
        }

        val save = todoRepository.save(findById)

        assertEquals(1,save.todono)
        assertEquals("update kim",save.title)
    }

    @Test
    @DisplayName("todoEntity delete test")
    fun testDelete(){
        val id : Long = 1
        todoRepository.deleteById(id)

        val findAll = todoRepository.findAll()

        assertEquals(true,findAll.isEmpty())
        assertEquals(0,findAll.size)
    }
}