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

            /*
            Teams.insert {
                it[name] = "team1"
                it[points] = 0
            }
            Teams.insert {
                it[name] = "team2"
                it[points] = 0
            }

             */
            Categories.insert{
                it[name]  ="Then there is JS"
            }
            Categories.insert{
                it[name]  ="Beard styles"
            }
            Categories.insert{
                it[name]  ="They took 'er jobs!"
            }
            Categories.insert{
                it[name]  ="That's Bruce"
            }
            Categories.insert{
                it[name]  ="Let me Google that"
            }

            // Events
            Answers.insert {
                it[answer]= "[] + []"
                it[question]= "What is \"\" (Empty string)"
                it[points] = 100
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "{} + []"
                it[question]= "What is 0"
                it[points] = 200
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "[] + {}"
                it[question]= "What is [object Object]"
                it[points] = 300
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "{} + {}"
                it[question]= "What is NaN (Not a Number)"
                it[points] = 400
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "Array(16).join(\"word\" -1) + \" Batman!\""
                it[question]= "What is \"NaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaN Batman!\""
                it[points] = 500
                it[categoryId] = 1
                it[done] = false
            }

            // Beards
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/PUP0s.jpg"
                it[question]= "What is a \"Power beard\""
                it[points] = 100
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/T879D.jpg"
                it[question]= "What is a shave (No beard)"
                it[points] = 200
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/x0dDC.jpg"
                it[question]= "What is a \"Horseshoe mustache\""
                it[points] = 300
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/0UXnj.png"
                it[question]= "What is a \"Chin strap\""
                it[points] = 400
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/96Act.jpg"
                it[question]= "What is a \"Short boxed beard\""
                it[points] = 500
                it[categoryId] = 2
                it[done] = false
            }

            // They took 'er jobs!
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/NlgG3.jpg"
                it[question]= "What is a Mink farmer"
                it[points] = 100
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/LarTQ.jpg"
                it[question]= "What is a teacher"
                it[points] = 200
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/XalEB.jpg"
                it[question]= "What is IT-Support"
                it[points] = 300
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/Yzne9.jpg"
                it[question]= "What is jobless (A-kasse)"
                it[points] = 400
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/YfJCu.jpg"
                it[question]= "What is a DJ/Producer"
                it[points] = 500
                it[categoryId] = 3
                it[done] = false
            }

            // That's Bruce
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/3vcwk.jpg"
                it[question]= "Who is Bruce Wayne"
                it[points] = 100
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/dLHT3.jpg"
                it[question]= "Who is Bruce Banner"
                it[points] = 200
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/CCZVz.jpg"
                it[question]= "Who is Bruce Lee"
                it[points] = 300
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/rdNAk.jpg"
                it[question]= "Who is Bruce Almighty"
                it[points] = 400
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/y0n02.jpg"
                it[question]= "Who is Bruce Gordon"
                it[points] = 500
                it[categoryId] = 4
                it[done] = false
            }

            // Let me Google that
            Answers.insert {
                it[answer]= "#!"
                it[question]= "What is \"Shebang\""
                it[points] = 100
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The date for \"International talk like a pirate day\""
                it[question]= "When is \"19th of September\""
                it[points] = 200
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https:// 011001110110111101101111011001110110110001100101.com"
                it[question]= "What is \"Google Binary\""
                it[points] = 300
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "#00FFFF"
                it[question]= "What is \"Cyan\""
                it[points] = 400
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.vps.w4.dk/5zf7w.png"
                it[question]= "What is \"Rickrolling\""
                it[points] = 500
                it[categoryId] = 5
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