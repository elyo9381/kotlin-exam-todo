package com.example.springdemo.entity

import com.example.springdemo.dto.TodoDto
import com.example.springdemo.mapstruct.TodoMapper
import com.example.springdemo.repository.TodoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

//@ExtendWith()
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class TodoTest @Autowired constructor(@Autowired private var todoRepository: TodoRepository){

    val todoMapper = Mappers.getMapper(TodoMapper::class.java)


    @BeforeEach
    fun before(){
        val dataDto1 = TodoDto().apply {
            this.titleName = "won"
            this.description = "hihihi~~~"
            this.schedule = "2021-08-17 10:25:00"
        }

        val dataTodo = todoMapper.toTodo(dataDto1)
        val save = todoRepository.save(dataTodo)

        val findById = todoRepository.findById(save.id!!)
        assertEquals( save.id , findById.get().id)

    }

    @Test
    fun testTodoSave() {
        val testDto = TodoDto().apply {
            this.titleName = "new save"
            this.description = "hihihi~~~"
            this.schedule = "2021-08-17 10:48:00"
        }

        val testTodo = todoMapper.toTodo(testDto)

        val saveTodo= todoRepository.save(testTodo)

        assertEquals( "new save", saveTodo.title )
    }

    @Test
    fun testReadTodo(){
        val id : Long = 1
        val findById = todoRepository.findById(id)
        assertEquals("new 2 data",findById.get().title)
    }


    @Test
    fun testUpdate(){
        val findById = todoRepository.findById(3).get()

        findById.apply {
            this.title = "kim"
        }

        val save = todoRepository.save(findById)

        assertEquals(3,save.id)
        assertEquals("kim",save.title)
    }

    @Test
    fun testDelete(){
        val id : Long = 3
        todoRepository.deleteById(id)

        val findAll = todoRepository.findAll()

        assertEquals(false,findAll.isEmpty())
        assertEquals(0,findAll.size)
    }
}