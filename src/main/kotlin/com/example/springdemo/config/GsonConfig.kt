package com.example.springdemo.config

import com.example.springdemo.util.IntTypeAdpter
import com.example.springdemo.util.LocalDateAdapter
import com.example.springdemo.util.LocalDateTimeAdapter
import com.example.springdemo.util.LocalTimeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Configuration
class GsonConfig {

    @Bean
    fun customConverters(): Gson = GsonBuilder()
        .registerTypeAdapter(Int::class.java, IntTypeAdpter())
        .registerTypeAdapter(Integer::class.java, IntTypeAdpter())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter() )
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter() )
        .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter() )
        .setDateFormat("yyyy-MM-dd HH:mm:ss").create()
}