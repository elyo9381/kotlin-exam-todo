package com.example.springdemo.common

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonWriter
import net.minidev.json.JSONArray
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.io.IOException

import java.io.StringWriter




class JSONTypeHandler : BaseTypeHandler<JsonArray?>() {

    /**
     * statement : sql문을 string으로 함수를 통해 정적인 시점에서 sql문을 전달하는 타입
     * PreparedStatement : sql을 ?과 함께 string으로 저장하고 이를 통해 동적으로 쿼리 전송이 가능한 타입
     *
     * sql문에서 변수가 들어갈 자리는 '?' 로 표시된다. 실행시에 ? 에 대응되는 값을 지정할때
     * setString(int parameterIndex,String X)
     * setInt(int parameterIndex,int x)
     * 와 같이 setXXX메소드를 통해서 설정한다.
     * 또한 PreparedStatement 는 SQL문에서 like키워드를 사용할 경우 사용할 수 없다.
     *
     * ResultSet: jdbc에서 쿼리를 실행하면 ResultSet 타입으로 결과를 반환 합니다.
     * 이를 통해 결과값을 저장 가능하며 저장된 값을 불러올수도 있습니다.
     *
     * CallableStatement :  데이터베이스 내의 스토어드 프로시저(Stored Procedure)를 호출할수있는 스테이트먼트
     *
     * Streams : gson에서 object model or stream을 통해서 json을 읽고 쓸수 있습니다.
     * Streaming access는  JsonReader, JsonWriter를 이용할수있습니다.
     * object model는 JsonElement class hierarchy를 이용할수있습니다.
     *
     *
     *
     */

    private val gson = Gson()

    override fun setNonNullParameter(ps: PreparedStatement?, i: Int, parameter: JsonArray?, jdbcType: JdbcType?) {
        try {
            // json을 만드는 과정
            val sw = StringWriter()
            val jsonWriter = JsonWriter(sw)
            jsonWriter.isLenient = false
            // Streams 는 JsonElement를 이용할수있으며 JsonArray를 이용할수있습니다.  JsonArray 생성
            Streams.write(parameter, jsonWriter)
            // jdbc에 sql 등록
            ps!!.setString(i, sw.toString())
        } catch (ex: IOException) {
            throw RuntimeException(ex.message, ex)
        }
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): JsonArray? {
        val string = rs?.getString(columnName)
        return gson.fromJson(string,JsonArray::class.java)
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): JsonArray? {
        val string = rs?.getString(columnIndex)
        return gson.fromJson(string,JsonArray::class.java)
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): JsonArray? {
        val string = cs?.getString(columnIndex)
        return gson.fromJson(string,JsonArray::class.java)
    }
}