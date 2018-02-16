package com.database.domain

import org.apache.commons.dbutils.BasicRowProcessor
import org.apache.commons.dbutils.BeanProcessor
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.ResultSetHandler
import org.apache.commons.dbutils.handlers.BeanHandler
import org.apache.commons.dbutils.handlers.BeanListHandler
import java.beans.PropertyDescriptor
import java.sql.ResultSetMetaData

open class Table{
    open val mapping:Map<String,String> = emptyMap()
}

class MyBeanProcessor : BeanProcessor(){

    override fun mapColumnsToProperties(rsmd: ResultSetMetaData?, props: Array<out PropertyDescriptor>?): IntArray {
        return super.mapColumnsToProperties(rsmd, props)
    }
}


inline fun <reified T> QueryRunner.findOne(sql: String): T {
    return BeanHandler(T::class.java).run { query(sql, this) }
}

inline fun <reified T> QueryRunner.findAll(sql: String): MutableList<T> {
    return BeanListHandler(T::class.java,BasicRowProcessor(BeanProcessor(mapOf("customer_id" to "customerId")))).run { query(sql, this) }
}

inline fun <reified T : Table> QueryRunner.findAll(): MutableList<T> {
    val mapping = T::class.java.newInstance().mapping
    var basicRowProcessor = BasicRowProcessor()
    if(!mapping.isEmpty()){
         basicRowProcessor = BasicRowProcessor(BeanProcessor(mapping))
    }
    return BeanListHandler(T::class.java, basicRowProcessor).run { query("Select * FROM ${T::class.simpleName}", this) }
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