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
    val imageHeight = 500
    val trueOrFalse = 200

    fun init() {
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
            Categories.insert {
                it[name] = "Injection Exploits"
            }
            Categories.insert {
                it[name] = "True or False"
            }
            Categories.insert {
                it[name] = "Port numbers as Binary"
            }
            Categories.insert {
                it[name] = "OS codenames"
            }
            Categories.insert {
                it[name] = "Linux man pages"
            }

            // Injection Exploits
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/qJWgc.jpg?h=$imageHeight"
                it[question] = "What is SQL Injection (SQLi)?"
                it[points] = 100
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/GaWIi.png?h=$imageHeight"
                it[question] = "What is Cross Site Script (XSS)?"
                it[points] = 200
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/Oe7AN.png?h=$imageHeight"
                it[question] = "What is Log4Shell (Log4J)?"
                it[points] = 300
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/Z5u1K.png?h=$imageHeight"
                it[question] = "What is ShellShock?"
                it[points] = 400
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/jicc1.jpg?h=$imageHeight"
                it[question] = "What is Eternal Blue?"
                it[points] = 500
                it[categoryId] = 1
                it[done] = false
            }


            //True or False
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/QGpsS.jpg?h=$trueOrFalse" // ArnoldC (True)
                it[question] = "What is True?"
                it[points] = 100
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/IVjli.jpg?h=$trueOrFalse" // LOLCODE
                it[question] = "What is False?"
                it[points] = 200
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/lfCrZ.jpg?h=$trueOrFalse" // Brain fuck
                it[question] = "What is True?"
                it[points] = 300
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/HFpiu.jpg?h=$trueOrFalse" // Whitespace
                it[question] = "What is False?"
                it[points] = 400
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer] = "https://images.vps.w4.dk/eLYtQ.jpg?h=$trueOrFalse" // Spakespeare
                it[question] = "What is False?"
                it[points] = 500
                it[categoryId] = 2
                it[done] = false
            }

            // Port numbers as Binary
            Answers.insert {
                it[answer] = "10101"
                it[question] = "What is FTP (21)?"
                it[points] = 100
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer] = "10110"
                it[question] = "What is SSH (22)?"
                it[points] = 200
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer] = "11001"
                it[question] = "What is SMTP (25)?"
                it[points] = 300
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer] = "1010000"
                it[question] = "What is HTTP (80)?"
                it[points] = 400
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer] = "110111011"
                it[question] = "What is HTTPS (443)?"
                it[points] = 500
                it[categoryId] = 3
                it[done] = false
            }

            // OS codenames
            Answers.insert {
                it[answer] = "The code name for Microsoft Windows XP, which was released in 2001."
                it[question] = "What is \"Whistler\"?"
                it[points] = 100
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer] = "The code name for Apple's macOS version 10.11, which was released in 2015."
                it[question] = "What is \"El Capitan\"?"
                it[points] = 200
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer] = "The code name for Android version 4.4, which was released in 2013."
                it[question] = "What is \"KitKat\"?"
                it[points] = 300
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer] = "The code name for Ubuntu version 18.04, which was released in 2018."
                it[question] = "What is \"Bionic Beaver\"?"
                it[points] = 400
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer] = "The code name for Google Chrome OS, which was was released on Chromebook Pixel in 2015."
                it[question] = "What is \"Samus\"?"
                it[points] = 500
                it[categoryId] = 4
                it[done] = false
            }

            // Linux man pages
            Answers.insert {
                it[answer] = "For each operand that names a file of a type other than directory"
                it[question] = "What is \"ls\"?"
                it[points] = 100
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer] = "is a flexible tool for interrogating DNS name servers."
                it[question] = "What is \"dig\"?"
                it[points] = 200
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer] =
                    "is a program for logging into a remote machine and for executing commands on a remote machine"
                it[question] = "What is \"ssh\"?"
                it[points] = 300
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer] =
                    "The utility is used to assign an address to a network interface and/or configure network interface parameters."
                it[question] = "What is \"ifconfig\"?"
                it[points] = 400
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer] = "is an open source tool for network exploration and security auditing"
                it[question] = "What is \"nmap\"?"
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
        block: () -> T
    ): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}