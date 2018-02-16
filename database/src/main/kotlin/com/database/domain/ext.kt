package com.database.domain

import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.ResultSetHandler
import org.apache.commons.dbutils.handlers.BeanHandler
import org.apache.commons.dbutils.handlers.BeanListHandler

inline fun <reified T> QueryRunner.findOne(sql: String): T {
    return BeanHandler(T::class.java).run { query(sql, this) }
}

inline fun <reified T> QueryRunner.findAll(sql: String): MutableList<T> {
    return BeanListHandler(T::class.java).run { query(sql, this) }
}

inline fun <reified T> QueryRunner.findAll(): MutableList<T> {
    return BeanListHandler(T::class.java).run { query("Select * FROM ${T::class.simpleName}", this) }
}


fun QueryRunner.query(sql: String): List<Map<String, Any?>>? {

    val resultSetHandler = ResultSetHandler<List<Map<String, Any?>>> { rs ->
        val meta = rs.metaData
        val cols = meta.columnCount
        val result = arrayListOf<Map<String,Any?>>()

        while (rs.next()) {
            val map = mutableMapOf<String, Any?>()
            for (i in 0 until cols) {
                val columnName = meta.getColumnName(i + 1)
                map[columnName] = rs.getObject(i + 1)
            }
            result.add(map)
        }

        result
    }

    return query(sql,resultSetHandler)
}