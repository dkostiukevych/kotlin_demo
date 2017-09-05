package com.database.domain

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = varchar("id", 10).primaryKey() // Column<String>
    val name = varchar("name", length = 50) // Column<String>
    val cityId = (integer("city_id") references Cities.id).nullable() // Column<Int?>
}