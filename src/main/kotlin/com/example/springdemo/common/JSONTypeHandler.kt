package com.example.springdemo.common

import com.google.gson.Gson
import com.google.gson.JsonArray
import net.minidev.json.JSONArray
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

class JSONTypeHandler : BaseTypeHandler<JsonArray?>() {

    private val gson = Gson()

    override fun setNonNullParameter(ps: PreparedStatement?, i: Int, parameter: JsonArray?, jdbcType: JdbcType?) {
        val toString = parameter.toString()
        if( parameter == null){
            ps.setString(i,null)
        }
        ps.setString(i,toString)
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): JsonArray? {
        val d = rs.getString(columnName)
        if(d == null) return d
        return gson.fromJson(columnName, JSONArray::class.java)
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): JsonArray? {
        val d = gson.fromJson(columnName, JSONArray::class.java)
        if(d == null) return d
        return d
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): JsonArray? {
        TODO("Not yet implemented")
    }
}