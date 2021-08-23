package com.example.springdemo.entity

import com.example.springdemo.config.GsonConfig
import com.example.springdemo.dto.PostDto
import com.example.springdemo.mapstruct.PostMapstruct
import com.example.springdemo.repository.PostRepository
import com.google.gson.Gson
import com.google.gson.JsonObject


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class PostEntityTes @Autowired constructor(@Autowired private var postRepository: PostRepository) {

    val postMapstruct = Mappers.getMapper(PostMapstruct::class.java)
    private val gson = Gson()

    private val customConverters = GsonConfig()



    @Test
    @DisplayName(" test postEntity save ")
    fun testSave(): PostEntity {

        val carStr = """
            {
                "modelName" : "k8",
                "modelColor" : "red",
                "modelPrice" : "2000",
                "description" : "just car"
            }
        """


        val changedGson = gson.fromJson(carStr, JsonObject::class.java)

        val testPostDto = PostDto().apply {
            this.name = "Won"
            this.title = "TitleWon"
            this.car = changedGson
            this.createdDate = LocalDateTime.now()
        }

        println(testPostDto)
        val toEntity = postMapstruct.toEntity(testPostDto)

        return postRepository.save(toEntity)

    }


    @Test
    @DisplayName(" test read one PostData")
    fun testRead() {

        val savedPost = testSave()

        val findById = postRepository.findById(savedPost.postno!!).get()

        println(findById)

        assertNotNull(findById)
        assertEquals("{\"modelName\":\"k8\",\"modelColor\":\"red\",\"modelPrice\":\"2000\",\"description\":\"just car\"}", findById.car.toString())
    }

    @Test
    @DisplayName(" test update PostData")
    fun testUpdate() {

        val testSave = testSave()


        val findById = postRepository.findById(testSave.postno!!).get()

        val carStr = """
            {
                "modelName" : "K100",
                "modelColor" : "red",
                "modelPrice" : "200000",
                "description" : "just car"
            }
        """

        val updateJson = gson.fromJson(carStr, JsonObject::class.java)


        findById.apply {
            this.car = updateJson
        }

        val updatedDate = postRepository.save(findById)

        assertNotNull(updatedDate)
        assertEquals("{\"modelName\":\"K100\",\"modelColor\":\"red\",\"modelPrice\":\"200000\",\"description\":\"just car\"}", findById.car.toString())
    }

}

