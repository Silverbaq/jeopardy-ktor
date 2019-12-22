package dk.w4.Repository

import dk.w4.Repository.DatabaseFactory.dbQuery
import dk.w4.model.Team
import dk.w4.model.Teams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.atomic.AtomicInteger

class TeamRepository : Repository<Team> {
    override suspend fun add(team: Team) {
        transaction {
            Teams.insert {
                it[name] = team.name
                it[points] = team.points
            }
        }
    }

    override suspend fun getById(id: Int): Team? = dbQuery {
        Teams.select {
            (Teams.id eq id)
        }.mapNotNull { toTeam(it) }.singleOrNull()
    }

    override suspend fun getById(id: String) = getById(id.toInt())

    override suspend fun getAll(): List<Team> = dbQuery {
        Teams.selectAll().map { toTeam(it) }
    }

    override suspend fun update(team: Team): Team {
        // TODO
        return team
    }

    override suspend fun removeById(id: Int): Boolean {
        if (getById(id) == null) {
            throw IllegalArgumentException("No team found for id $id")
        }
        return dbQuery {
            Teams.deleteWhere { Teams.id eq id } > 0
        }
    }

    override suspend fun removeById(id: String) = removeById(id.toInt())

    override suspend fun clear() {
        Teams.deleteAll()
    }

    private fun toTeam(row: ResultRow): Team =
        Team(
            id = row[Teams.id].value,
            name = row[Teams.name],
            points = row[Teams.points]
        )
}