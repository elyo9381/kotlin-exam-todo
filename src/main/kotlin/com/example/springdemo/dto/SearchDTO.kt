package com.example.springdemo.dto

open class SearchDTO(var page:Int = 0, var limit:Int = 20) {

    constructor() : this(0, 20)

    val start get() = (page-1)*limit
    val startPage get() = (page-1)
}