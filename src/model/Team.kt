package dk.w4.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class Team(val id: Int, val name: String, val points: Int): Serializable

object Teams: IntIdTable(){
    val name: Column<String> = varchar("name", 255)
    val points: Column<Int> = integer("points")
}