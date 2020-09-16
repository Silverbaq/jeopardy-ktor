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
                it[name]  ="My Favorites"
            }
            Categories.insert{
                it[name]  ="Born’n’rasied"
            }
            Categories.insert{
                it[name]  ="GameBoy Accessories"
            }
            Categories.insert{
                it[name]  ="IT you say... Easy!"
            }
            Categories.insert{
                it[name]  ="Fun facts"
            }
            Categories.insert {
                it[name] = "Computer ports as binaries"
            }

            // My Favorites
            Answers.insert {
                it[answer]= "https://i.ibb.co/MCmZThf/lasagne.jpg"
                it[question]= "What is lasagne"
                it[points] = 100
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/Yt0Tx4Z/siliconvalley.jpg"
                it[question]= "What is Silicon Valley"
                it[points] = 200
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/DVpT4gk/kotlin.png"
                it[question]= "What is Kotlin"
                it[points] = 300
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/JQ4VG5X/sevablodda.jpg"
                it[question]= "What is Sevablødda"
                it[points] = 400
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/3FH5rL5/darknet.jpg"
                it[question]= "What is Darknet diaries"
                it[points] = 500
                it[categoryId] = 1
                it[done] = false
            }

            // Born’n’rasied
            Answers.insert {
                it[answer]= "Name of the local railroad from my childhood. Which also has a “famous” danish song."
                it[question]= "What is VLTJ"
                it[points] = 100
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The city where I was raised "
                it[question]= "What is Vrist"
                it[points] = 200
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The city where I went to primary school"
                it[question]= "What is Harboøre"
                it[points] = 300
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The hospital where I was born"
                it[question]= "What is Holstebro"
                it[points] = 400
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The city I started playing basketball in"
                it[question]= "What is Lemvig"
                it[points] = 500
                it[categoryId] = 2
                it[done] = false
            }

            // GameBoy Accessories
            Answers.insert {
                it[answer]= "https://i.ibb.co/87FqZLr/symaskine.jpg"
                it[question]= "What is the Game Boy sewing machine"
                it[points] = 100
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/1mFvLTk/Fishing-Accessory.jpg"
                it[question]= "What is the Game Boy Sonar"
                it[points] = 200
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/FV5JDfM/Game-Boy-Printer.jpg"
                it[question]= "What is the Game Boy Printer"
                it[points] = 300
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/4FTQ529/Game-Boy-Camera.jpg"
                it[question]= "What is the Game Boy Camera"
                it[points] = 400
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/SRGbdTQ/Pedi-Sedate.jpg"
                it[question]= "What is the Pedi Sedater"
                it[points] = 500
                it[categoryId] = 3
                it[done] = false
            }

            // IT you say... Easy!
            Answers.insert {
                it[answer]= "https://i.ibb.co/3TnHh36/mastersystem.jpg"
                it[question]= "What is the Sega Master System"
                it[points] = 100
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/zSZhKSK/486.jpg"
                it[question]= "What is the 80486 (66 Mhz, 4 MB RAM"
                it[points] = 200
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/p2fq0Jd/1024px-Amstrad-CPC464.jpg"
                it[question]= "What is the Amstrad CPC 464"
                it[points] = 300
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/bBrtHZz/hero.jpg"
                it[question]= "What is HTC Hero"
                it[points] = 400
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "Array(16).join('wat' - 1) + ' Batman!'"
                it[question]= "What is \"NaNNaNNaNNaNNaNNaNNaNNaNNaN\nNaNNaNNaNNaNNaNNaN Batman!\""
                it[points] = 500
                it[categoryId] = 4
                it[done] = false
            }

            // Fun Facts
            Answers.insert {
                it[answer]= "The day I was born"
                it[question]= "What is the 18th of Septemper, 1983"
                it[points] = 100
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The amount of years lived in Odense"
                it[question]= "What is 12 years"
                it[points] = 200
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "My profession, before I moved to Odense"
                it[question]= "What is a Minkfarmer"
                it[points] = 300
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ibb.co/xCWDCL4/93points.jpg"
                it[question]= "What is 93 points"
                it[points] = 400
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The only exam Ive have ever cheated in"
                it[question]= "What is Advanced Operating system"
                it[points] = 500
                it[categoryId] = 5
                it[done] = false
            }

            // Computer ports as binaries
            Answers.insert {
                it[answer]= "The port number for FTP (21)"
                it[question]= "What is 10101"
                it[points] = 100
                it[categoryId] = 6
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The port number for SSH (22)"
                it[question]= "What is 10110"
                it[points] = 200
                it[categoryId] = 6
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The port number for HTTP (80)"
                it[question]= "What is 1010000"
                it[points] = 300
                it[categoryId] = 6
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The port number for HTTPS (443)"
                it[question]= "What is 110111011"
                it[points] = 400
                it[categoryId] = 6
                it[done] = false
            }
            Answers.insert {
                it[answer]= "The port number for MySQL (3306)"
                it[question]= "What is 110011101010"
                it[points] = 500
                it[categoryId] = 6
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