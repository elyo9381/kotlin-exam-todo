package com.example.springdemo

import com.example.springdemo.Aspect.Performance
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class SpringDemoApplication

fun main(args: Array<String>) {

    runApplication<SpringDemoApplication>(*args){
        setBannerMode(Banner.Mode.OFF)
    }
}
