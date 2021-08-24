package com.example.springdemo.common

import com.example.springdemo.config.GsonConfig
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.slf4j.LoggerFactory
import javax.persistence.AttributeConverter

class JsonArrayConverter : AttributeConverter<JsonArray?, String> {

    private val gson = GsonConfig()

    override fun convertToDatabaseColumn(attribute: JsonArray?): String? {
        if( attribute == null ) return null
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): JsonArray? {
        if( dbData == null ) return null
        return gson.customConverters().fromJson(dbData, JsonArray::class.java)
    }
}