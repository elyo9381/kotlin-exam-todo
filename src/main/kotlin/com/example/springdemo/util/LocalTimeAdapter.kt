package com.example.springdemo.util

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeAdapter : JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    override fun serialize(src: LocalTime?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement? {
        return if(src == null) null else JsonPrimitive( DateTimeFormatter.ofPattern("HH:mm:ss").format(src) )
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalTime {
        return LocalTime.parse(json?.asString,  DateTimeFormatter.ofPattern("HH:mm:ss") )
    }
}