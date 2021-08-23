package com.example.springdemo.util

import com.example.springdemo.controller.api.TodoController
import com.google.gson.*
import org.slf4j.LoggerFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class    LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {


    private val log = LoggerFactory.getLogger(LocalDateTimeAdapter::class.java)

    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement? {
        return if(src == null) null else JsonPrimitive( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(src) )
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {

        return LocalDateTime.parse(json?.asString,  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") )
    }
}