package com.example.springdemo.Aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class Performance {

    private val log = LoggerFactory.getLogger(Performance::class.java)

    @Pointcut("execution(* com.example.springdemo.service.UserService.userLists(..))")
    fun getUsers(){}

    @Pointcut("execution(* com.example.springdemo.service.UserService.userSave(..))")
    fun userSave(){}

    @Pointcut("execution(* com.example.springdemo.service.UserService.users(..))")
    fun myBatisGetUsers(){}

    @Around("getUsers() || userSave() || myBatisGetUsers()")
    fun calculatePerformanceTime(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        var result: Any? = null
        try {
            val start = System.currentTimeMillis()
            result = proceedingJoinPoint.proceed()
            val end = System.currentTimeMillis()
            log.info("수행 시간 : {}", (end - start))
        } catch (throwable: Throwable) {
            log.info("exception! ")
        }
        return result
    }

}