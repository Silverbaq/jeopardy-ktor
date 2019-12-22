package dk.w4.Repository

import dk.w4.Repository.AnswerRepository.Companion.toAnswer
import dk.w4.Repository.DatabaseFactory.dbQuery
import dk.w4.model.Answer
import dk.w4.model.Answers
import dk.w4.model.Categories
import dk.w4.model.Category
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository : Repository<Category> {
    override suspend fun add(data: Category) {
        transaction {
            Categories.insert {
                it[name] = data.name
            }
        }
    }

    override suspend fun getById(id: Int): Category? = dbQuery {
         Categories.select { Categories.id eq id }.mapNotNull {
             val answers = Answers.select{ Answers.categoryId eq id }.map { toAnswer(it) }
             toCategory(it, answers)
         }.singleOrNull()
    }

    override suspend fun getById(id: String) = getById(id.toInt())

    override suspend fun getAll(): List<Category> = dbQuery {
        Categories.selectAll().map {
            val answers = Answers.select{ Answers.categoryId eq it[Categories.id] }.map { toAnswer(it) }

            toCategory(it, answers)
        }

    }

    override suspend fun update(item: Category): Category {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return item
    }

    override suspend fun removeById(id: Int): Boolean {
        if (getById(id) == null) {
            throw IllegalArgumentException("No category by id $id")
        }
        return dbQuery {
            Categories.deleteWhere { Categories.id eq id } > 0
        }
    }

    override suspend fun removeById(id: String) = removeById(id.toInt())

    override suspend fun clear() {
        Categories.deleteAll()
    }

    private fun toCategory(row: ResultRow, answers: List<Answer>): Category =
        Category(
            id = row[Categories.id],
            name = row[Categories.name],
            answers = answers
        )
}