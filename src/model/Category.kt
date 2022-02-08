package dk.w4.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.io.Serializable

data class Category(val id: Int, val name: String, val answers: List<Answer> = mutableListOf()) : Serializable

object Categories: Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(id)
}

