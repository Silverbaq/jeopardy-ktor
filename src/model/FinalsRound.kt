package dk.w4.model
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.io.Serializable

data class FinalsRound (
    val id: Int,
    val category: String,
    val question: String,
    val answer: String
) : Serializable

object FinalsRounds : Table() {
    val id  = integer("id").autoIncrement().primaryKey()
    val category: Column<String> = varchar("category", 255)
    val question: Column<String> = varchar("question", 1000)
    val answer: Column<String> = varchar("answer", 1000)
}
