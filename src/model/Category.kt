package dk.w4.model

import dk.w4.model.Answers.autoIncrement
import dk.w4.model.Answers.primaryKey
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.io.Serializable

data class Category(val id: Int, val name: String, val answers: List<Answer> = mutableListOf()) : Serializable

object Categories: Table(){
    val id  = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 255)
}

