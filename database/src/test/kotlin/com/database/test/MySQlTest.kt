package com.database.test;

import com.database.domain.Cities
import com.database.domain.Users
import com.database.domain.db.utils.City
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanHandler
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test


class MySQlTest : BaseTest() {


    @Test
    fun testName() {
        Database.connect(postgre.getJdbcUrl(),
                driver = "org.postgresql.Driver",
                user = postgre.getUsername(),
                password = postgre.getPassword())


        transaction {
            create(Cities, Users)

            Cities.insert {
                it[name] = "St. Petersburg"
            }

            Cities.insert {
                it[name] = "Munich"
            }

            Cities.insert {
                it[name] = "Prague"
            }

            println("All cities:")

            for (city in Cities.selectAll()) {
                println("${city[Cities.id]}: ${city[Cities.name]}")
            }
        }
        println("Hello")


        //val h = BeanHandler<com.database.domain.db.utils.Cities>(com.database.domain.db.utils.Cities::class.java)

    }

    @Test
    fun catSelect() {

        val beanHandler = BeanHandler<City>(City::class.java)
        val city: City = QueryRunner().query("SELECT * FROM city", beanHandler)


        val city2: City = QueryRunner().findOne("SELECT * FROM city")

    }
}

inline fun <reified T> QueryRunner.findOne(sql: String): T {
    return BeanHandler(T::class.java).run { query(sql, this) }
}

inline fun <reified T> QueryRunner.findAll(sql: String): MutableList<T> {
    return BeanListHandler(T::class.java).run { query(sql, this) }
}