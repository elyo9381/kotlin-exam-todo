package com.example.springdemo.validator

import com.example.springdemo.annotation.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDateTimeValidator : ConstraintValidator<StringFormatDateTime, String> {


    private var patten : String? = null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.patten = constraintAnnotation?.patten
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try{
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(patten))
            true
        } catch (e : Exception){
            false
        }
    }
}