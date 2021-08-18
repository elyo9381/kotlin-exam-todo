package com.example.springdemo.dto


data class PageDto(
    // 현재페이지
    var currentPage: Int,

    // 페이지당 출력할 페이지 갯수
    var cntPerPage: Int,

    // 화면 하단 페이지 사이즈 1~10, 10~20 20~30 ...
    var pageSize: Int,

    // 전체 데이터 개수
    private var totalRecordCount: Int = 0,

    // 전체 페이지 개수
    var totalPageCount: Int = 0,

    // 페이지 리스트의 첫 페이지 번호
    var firstPage: Int = 0,

    // 페이지 리스트의 마지막 페이지 번호
    var lastPage: Int = 0,

    // SQL의 조건절에 사용되는 첫 RNUM
    var firstRecordIndex: Int = 0,

    // SQL의 조건절에 사용되는 마지막 RNUM
    var lastRecordIndex: Int = 0,

    // 이전 페이지 존재 여부
    var isHasPreviousPage: Boolean = false,

    // 다음 페이지 존재 여부
    var isHasNextPage: Boolean = false,

    var keyword : String = ""
){

    constructor(currentPage: Int, cntPerPage: Int, pageSize: Int, keyword: String) :
            this(currentPage, cntPerPage, pageSize, 0, 0,0, 0, 0, 0, false,false, keyword)

    fun addTotalRecordCount(totalRecordCount: Int) {
        this.totalRecordCount = totalRecordCount
        if (totalRecordCount > 0) {
            calculation()
        }
    }

    private fun calculation() {

        // 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장)
        totalPageCount = (totalRecordCount - 1) / cntPerPage + 1
        if (currentPage > totalPageCount) {
            currentPage = totalPageCount
        }

        // 페이지 리스트의 첫 페이지 번호
        firstPage = (currentPage - 1) / pageSize * pageSize + 1

        // 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장)
        lastPage = firstPage + pageSize - 1
        if (lastPage > totalPageCount) {
            lastPage = totalPageCount
        }

        // SQL의 조건절에 사용되는 첫 RNUM
        firstRecordIndex = (currentPage - 1) * cntPerPage

        // SQL의 조건절에 사용되는 마지막 RNUM
        lastRecordIndex = currentPage * cntPerPage

        // 이전 페이지 존재 여부
        isHasPreviousPage = firstPage != 1
        if (!isHasPreviousPage) {
            isHasPreviousPage = currentPage != firstPage
        }

        // 다음 페이지 존재 여부
        isHasNextPage = lastPage * cntPerPage < totalRecordCount
        if (!isHasNextPage) {
            //마지막 페이지에서 현재페이지가 마지막 페이지가 아닌경우 next처리
            isHasNextPage = currentPage != lastPage
        }
    }

    fun getTotalRecordCount(): Int {
        return totalRecordCount
    }


    init {
        //강제입력방지
        var currentPage = currentPage
        var cntPerPage = cntPerPage
        var pageSize = pageSize
        if (currentPage < 1) {
            currentPage = 1
        }
        //10,20,30개 단위 이외 처리 방지
        if (cntPerPage != 10 && cntPerPage != 20 && cntPerPage != 30) {
            cntPerPage = 10
        }
        // 하단 페이지 갯수 10개로 제한
        if (pageSize != 10) {
            pageSize = 10
        }
        this.currentPage = currentPage
        this.cntPerPage = cntPerPage
        this.pageSize = pageSize
    }
}
