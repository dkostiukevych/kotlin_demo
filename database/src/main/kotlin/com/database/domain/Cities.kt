package com.database.domain

import org.jetbrains.exposed.sql.Table

object Cities : Table() {
    val id = integer("id").autoIncrement().primaryKey() // Column<Int>
    val name = varchar("name", 50) // Column<String>
}
