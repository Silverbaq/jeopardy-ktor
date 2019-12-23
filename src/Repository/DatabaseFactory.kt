package dk.w4.Repository

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dk.w4.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(){
        Database.connect(hikari())

        transaction {
            SchemaUtils.create(Teams)
            SchemaUtils.create(Categories)
            SchemaUtils.create(Answers)

            Teams.insert {
                it[name] = "team1"
                it[points] = 0
            }
            Teams.insert {
                it[name] = "team2"
                it[points] = 0
            }
            Categories.insert{
                it[name]  ="something"
            }
            Categories.insert{
                it[name]  ="something else"
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 100
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 200
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 300
                it[categoryId] = 1
                it[done] = true
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 400
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 500
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 100
                it[categoryId] = 2
                it[done] = true
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 200
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 300
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "what what what"
                it[question]= "then something"
                it[points] = 400
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://heartbeats.dk/wp-content/uploads/2019/10/funny-game-of-thrones-memes.jpg"
                it[question]= "then something"
                it[points] = 500
                it[categoryId] = 2
                it[done] = false
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(
        block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}