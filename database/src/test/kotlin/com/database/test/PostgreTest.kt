package com.database.test

import com.database.domain.Cities
import com.database.domain.Users
import com.database.domain.db.utils.City
import me.tatarka.assertk.assertions.hasSize
import me.tatarka.assertk.assertions.isEqualTo
import org.apache.commons.dbutils.QueryRunner
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Before
import org.junit.Test

class PostgreTest : BaseTest() {

    @Before
    fun setUp() {
        Database.connect(postgre.getJdbcUrl(),
                driver = "org.postgresql.Driver",
                user = postgre.getUsername(),
                password = postgre.getPassword())


        transaction {
            SchemaUtils.create(Cities, Users)

            com.database.domain.Cities.insert {
                it[name] = "St. Petersburg"
            }

            com.database.domain.Cities.insert {
                it[name] = "Munich"
            }

            com.database.domain.Cities.insert {
                it[name] = "Prague"
            }
        }
        println("DB is full")
    }

    @Test
    fun testCanGetAllCities() {

        val query = """
                SELECT *
                FROM cities
        """

        val cities  = db.findAll<City>(query)
        println(cities)
    }


    @Test
    fun testCanGetOneCity() {
        val id = 2

        val query = """
            SELECT *
            FROM cities
            WHERE id = $id
        """
        val city = db.findOne<City>(query)
        me.tatarka.assertk.assert(city.name).isEqualTo("Munic")
    }

    @Test
    fun testCanGetAllCitiesEnhanced() {
        val cityRepo = CityRepository(db)
        val cityList = cityRepo.findAll()
        me.tatarka.assertk.assert(cityList).hasSize(3)
    }

    @Test
    fun testCanGetOneCityEnhanced() {
        val id = 2
        val cityRepo = CityRepository(db)
        val city = cityRepo.findById(id)
        me.tatarka.assertk.assert(city.name).isEqualTo("Munic")
    }
}

class CityRepository(private val db:QueryRunner){

    fun findById(id:Int):City{
        val query = """
            SELECT *
            FROM cities
            WHERE id = $id
        """

        return db.findOne(query)
    }

    fun findAll():MutableList<City>{
        val query = """
                SELECT *
                FROM cities
        """
        return db.findAll(query)
    }
}