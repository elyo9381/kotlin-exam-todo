package com.example.springdemo.dto

import java.time.LocalDateTime

data class RequestPageDto(
    // 현재페이지
    var currentPage: Int,

    // 페이지당 출력할 페이지 갯수
    var cntPerPage: Int,

    // 화면 하단 페이지 사이즈 1~10, 10~20 20~30 ...
    var pageSize: Int,

    var keyword : String = "",

    var fromDate : LocalDateTime? = null,

    var toDate : LocalDateTime? = null

){
    constructor(currentPage: Int, cntPerPage: Int, pageSize: Int) : this(currentPage, cntPerPage, pageSize,"",null,null)

    constructor(currentPage: Int, cntPerPage: Int, pageSize: Int,keyword: String) : this(currentPage, cntPerPage, pageSize,keyword,null,null)

}
