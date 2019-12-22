package dk.w4.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.io.Serializable

data class Answer(
    val id: Int,
    val question: String,
    val answer: String,
    val points: Int,
    val categoryId: Int,
    val done: Boolean
) : Serializable

object Answers : Table() {
    val id  = integer("id").autoIncrement().primaryKey()
    val question: Column<String> = varchar("question", 1000)
    val answer: Column<String> = varchar("answer", 1000)
    val points: Column<Int> = integer("points")
    val categoryId = (integer("category_id") references Categories.id) //reference("category_id", Categories.id)
    val done: Column<Boolean> = bool("done")
}