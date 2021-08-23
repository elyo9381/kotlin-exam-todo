package com.example.springdemo.util

import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.NumberFormatException

class IntTypeAdpter : TypeAdapter<Number>() {

    override fun write(out: JsonWriter?, value: Number?) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Number? {
        if( `in`?.peek() == JsonToken.NULL ) {
            `in`?.nextNull()
            return null
        }

        try {
            val result = `in`?.nextString()
            if("" == result) return null
            return result?.toInt() ?: null
        }catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

}