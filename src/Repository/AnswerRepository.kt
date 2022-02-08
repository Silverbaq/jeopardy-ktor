package dk.w4.Repository

import dk.w4.Repository.DatabaseFactory.dbQuery
import dk.w4.model.Answer
import dk.w4.model.Answers
import dk.w4.model.Categories
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class AnswerRepository : Repository<Answer> {
    override suspend fun add(data: Answer) {
        transaction {
            Answers.insert {
                it[answer] = data.answer
                it[question] = data.question
                it[categoryId] = data.categoryId
                it[points] = data.points
                it[done] = false
            }
        }
    }

    override suspend fun getById(id: Int): Answer? = dbQuery {
        Answers.select { (Answers.id eq id) }.mapNotNull { toAnswer(it) }.singleOrNull()
    }

    override suspend fun getById(id: String) = getById(id.toInt())

    override suspend fun getAll(): List<Answer> = dbQuery {
        Answers.selectAll().mapNotNull { toAnswer(it) }
    }

    override suspend fun update(item: Answer) {
        transaction {
            Answers.update({ Answers.id eq item.id }){
                it[points] = item.points
                it[categoryId] = item.categoryId
                it[answer] = item.answer
                it[question] = item.question
                it[done] = item.done
            }
        }
    }

    override suspend fun removeById(id: Int): Boolean {
        if (getById(id) == null) {
            throw IllegalArgumentException("No answer by id $id")
        }
        return dbQuery {
            Answers.deleteWhere { (Answers.id eq id) } > 0
        }
    }

    override suspend fun removeById(id: String) = removeById(id.toInt())

    override suspend fun clear() {
        Answers.deleteAll()
    }

    companion object {
        fun toAnswer(row: ResultRow): Answer =
            Answer(
                id = row[Answers.id],
                question = row[Answers.question],
                answer = row[Answers.answer],
                points = row[Answers.points],
                categoryId = row[Answers.categoryId],
                done = row[Answers.done]
            )
    }
}