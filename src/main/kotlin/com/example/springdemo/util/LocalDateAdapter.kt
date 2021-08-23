package com.example.springdemo.util

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    override fun serialize(src: LocalDate?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement? {
        return if(src == null) null else JsonPrimitive( DateTimeFormatter.ofPattern("yyyy-MM-dd").format(src) )
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
        return LocalDate.parse(json?.asString,  DateTimeFormatter.ofPattern("yyyy-MM-dd") )
    }
}