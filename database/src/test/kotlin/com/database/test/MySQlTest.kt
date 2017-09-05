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
import org.postgresql.ds.PGSimpleDataSource


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


        val dataSource = PGSimpleDataSource()
        dataSource.user = "test"
        dataSource.password = "test"
        dataSource.url = postgre.getJdbcUrl()


        val runner = QueryRunner(dataSource)
        //val h = BeanHandler<com.database.domain.db.utils.Cities>(com.database.domain.db.utils.Cities::class.java)

        val h: BeanListHandler<City> = rows()

        val id = 2

        val query = """
            SELECT *
            FROM cities
            WHERE id = $id
        """

        val city: City = runner.findOne(query)
        println("${city.id} -> ${city.name}")

        val cities: MutableList<City> = runner.findAll("""
                        SELECT *
                        FROM cities
        """)
        println(cities)
    }

    private inline fun <reified T> row(): BeanHandler<T> {
        return BeanHandler(T::class.java)
    }

    private inline fun <reified T> rows(): BeanListHandler<T> {
        return BeanListHandler(T::class.java)
    }
}


inline fun <reified T> QueryRunner.findOne(sql: String): T {
    return BeanHandler(T::class.java).run { query(sql, this) }
}

inline fun <reified T> QueryRunner.findAll(sql: String): MutableList<T> {
    return BeanListHandler(T::class.java).run { query(sql, this) }
}