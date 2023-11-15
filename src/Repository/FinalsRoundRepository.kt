package dk.w4.Repository

import dk.w4.Repository.DatabaseFactory.dbQuery
import dk.w4.model.FinalsRound
import dk.w4.model.FinalsRounds
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class FinalsRoundRepository : Repository<FinalsRound> {
    override suspend fun add(data: FinalsRound) {
        transaction {
            FinalsRounds.insert {
                it[category] = data.category
                it[question] = data.question
                it[answer] = data.answer
            }
        }
    }

    override suspend fun getById(id: Int): FinalsRound? = dbQuery {
        FinalsRounds.select { FinalsRounds.id eq id }.mapNotNull { toFinalsRound(it) }.singleOrNull()
    }

    override suspend fun getById(id: String): FinalsRound? = getById(id.toInt())

    override suspend fun getAll(): List<FinalsRound> = dbQuery {
        FinalsRounds.selectAll().mapNotNull { toFinalsRound(it) }
    }

    override suspend fun removeById(id: Int): Boolean {
        if (getById(id) == null) {
            throw IllegalArgumentException("No finals round by id $id")
        }
        return dbQuery { FinalsRounds.deleteWhere { FinalsRounds.id eq id } > 0 }
    }

    override suspend fun removeById(id: String): Boolean = removeById(id.toInt())

    override suspend fun clear(): Unit = dbQuery {
        FinalsRounds.deleteAll()
    }

    override suspend fun update(item: FinalsRound): Unit = dbQuery {
        FinalsRounds.update({ FinalsRounds.id eq item.id }) {
            it[category] = item.category
            it[question] = item.question
            it[answer] = item.answer
        }
    }

    companion object {
        fun toFinalsRound(row: ResultRow): FinalsRound =
            FinalsRound(
                id = row[FinalsRounds.id],
                category = row[FinalsRounds.category],
                question = row[FinalsRounds.question],
                answer = row[FinalsRounds.answer]
            )
    }
}