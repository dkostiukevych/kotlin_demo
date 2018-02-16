package com.database.test

import com.database.domain.findAll
import com.database.domain.query
import net.oddpoet.expect.extension.haveSizeOf
import net.oddpoet.expect.should
import org.apache.commons.dbutils.ResultSetHandler

import org.junit.Test

class CrudTest :BaseTest() {

    @Test
    fun testCanCreateTable() {
        val sql = """
         CREATE TABLE customers (
                customer_id   INTEGER UNIQUE,
                customer_name VARCHAR(50),
                balance       DECIMAL(7,2));
     """

        db.execute(sql)
        db.execute("INSERT INTO customers VALUES (1,'Ivan',45)")
        db.execute("INSERT INTO customers VALUES (2,'Dima',65)")

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


        db.query("SELECT * FROM customers", resultSetHandler).should.haveSizeOf(2)
        db.query("SELECT * FROM customers").should.haveSizeOf(2)


        val list = db.findAll<Customers>().first { it.customer_name != "Dima" }

        print(list)
    }
}

data class Customers(var customer_id: Long? = null,
                     var customer_name: String? = null)