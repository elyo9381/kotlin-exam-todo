package com.example.springdemo.common

import com.example.springdemo.config.GsonConfig
import com.google.gson.JsonObject
import org.slf4j.LoggerFactory
import javax.persistence.AttributeConverter

class JsonConver : AttributeConverter<JsonObject?, String> {

    private val logger = LoggerFactory.getLogger( JsonConver::class.java )
    private val gson = GsonConfig()


    override fun convertToDatabaseColumn(attribute: JsonObject?): String? {
        if( attribute == null ) return null
        return attribute.toString()

//        try {
//            return jacksonObjectMapper().writeValueAsString(attribute);
//        } catch (e : JsonProcessingException){
//            throw IllegalArgumentException("error log...")
//        }
    }

    override fun convertToEntityAttribute(dbData: String?): JsonObject? {

        if( dbData == null ) return null
        return gson.customConverters().fromJson(dbData, JsonObject::class.java)
//
//        try {
//            return jacksonObjectMapper().readValue(dbData,JsonObject::class.java);
//        } catch (e : IOException){
//            throw IllegalArgumentException("error log...")
//        }
    }


}