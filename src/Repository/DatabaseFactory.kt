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
                it[name]  ="Året der gik"
            }
            Categories.insert{
                it[name]  ="The future"
            }
            Categories.insert{
                it[name]  ="Misc"
            }
            Categories.insert{
                it[name]  ="Hold my beer, I got this!?!"
            }
            Categories.insert{
                it[name]  ="It's over 9k"
            }

            // Året der gik
            Answers.insert {
                it[answer]= "https://www.thoughtco.com/thmb/pG4De9t7aRBTSx-aGdaiEDO-F4E=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-573083127-593894665f4a4ae7b923d6b9b2058edb.jpg"
                it[question]= "Hvem er/skal gift(es)"
                it[points] = 100
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.redd.it/1w7d067081w21.png"
                it[question]= "Hvad er \"Sonic the hedgehog (2019)\""
                it[points] = 200
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://lumiere-a.akamaihd.net/v1/images/star-wars-the-rise-of-skywalker-theatrical-poster-1000_ebc74357.jpeg"
                it[question]= "Hvad er den sidste film i Star Wars historien (The rise of Skywalker)"
                it[points] = 300
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://cdn.cnn.com/cnnnext/dam/assets/191121212606-tesla-cybertruck-exlarge-169.jpg"
                it[question]= "Hvad er \"Tesla Truck\""
                it[points] = 400
                it[categoryId] = 1
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://bt.bmcdn.dk/media/cache/resolve/image_1240/image/124/1240630/22559564-four-hills-ski-jumping-tournam.jpeg"
                it[question]= "Hvad er \"1. Januar 2019\""
                it[points] = 500
                it[categoryId] = 1
                it[done] = false
            }

            //The future
            Answers.insert {
                it[answer]= "https://ekstrabladet.dk/migration_catalog/article3918567.ece/IMAGE_ALTERNATES/relationBig/delorean"
                it[question]= "Hvad er \"Tilbage til fremtiden (Back to the future)\""
                it[points] = 100
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://dygtyjqp7pi0m.cloudfront.net/i/35892/30933362_2.jpg?v=8D608586A9E3000"
                it[question]= "Hvad er \"Time machine\""
                it[points] = 200
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.ebayimg.com/images/g/lHMAAOxydlFSxp~h/s-l300.jpg"
                it[question]= "Hvad er \"Dr. Who\""
                it[points] = 300
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i1.wp.com/snesaday.com/wp-content/uploads/Teenage-Mutant-Ninja-Turtles-IV-Turtles-in-Time-FI-e1455392989805.png?resize=1024%2C576&ssl=1"
                it[question]= "Hvad er \"Turtles in time\""
                it[points] = 400
                it[categoryId] = 2
                it[done] = false
            }
            Answers.insert {
                it[answer]= "Stiv pik og vind i håret"
                it[question]= "Hvad er 2020"
                it[points] = 500
                it[categoryId] = 2
                it[done] = false
            }

            // Misc
            Answers.insert {
                it[answer]= "42"
                it[question]= "Hvad er svaret på livet, universet og det hele."
                it[points] = 100
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "1.5V"
                it[question]= "Hvad er spændingen for et AA batteri"
                it[points] = 200
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "nnamhoL"
                it[question]= "Hvad er \"Lohmann\" stavet spejlvendt"
                it[points] = 300
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://i.imgur.com/FR2nN.jpg"
                it[question]= "Hvad er GameBoy symaskinen"
                it[points] = 400
                it[categoryId] = 3
                it[done] = false
            }
            Answers.insert {
                it[answer]= "18-09-1983"
                it[question]= "Hvad er Steffens fødselsdag"
                it[points] = 500
                it[categoryId] = 3
                it[done] = false
            }

            // Hold my beer, I got this!?!
            Answers.insert {
                it[answer]= "https://www.sentinelone.com/wp-content/uploads/2019/05/10-Sean-Dillons-Ruby-script.jpg"
                it[question]= "Hvad er \"Eternal blue\""
                it[points] = 100
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://helloacm.com/wp-images/acm/2012/bf2.jpg"
                it[question]= "Hvad er \"Brain fuck\""
                it[points] = 200
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://lineastone.co.nz/wp-content/uploads/fly-images/350/1141-Pure-White-400x300-cc.jpg"
                it[question]= "Hvad er \"White-space\""
                it[points] = 300
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQsoF8udf_4-bXaDfXUmiyV77u6iY3yX1AIe3xEKGCh-BH4_Xct"
                it[question]= "Hvad er \"JSFuck\""
                it[points] = 400
                it[categoryId] = 4
                it[done] = false
            }
            Answers.insert {
                it[answer]= "Array(16).join('wat' - 1) + ' Batman!'"
                it[question]= "Hvad er \"NaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaNNaN Batman!\""
                it[points] = 500
                it[categoryId] = 4
                it[done] = false
            }

            // It's over 9k
            Answers.insert {
                it[answer]= "http://team-sort.dk/student/Michael_Sort_student23.jpg"
                it[question]= "Hvem er \"Michael Sort\""
                it[points] = 100
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "http://team-sort.dk/student/Michael_Sort_student03.jpg"
                it[question]= "Hvem er \"Michael Sort\""
                it[points] = 200
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "http://team-sort.dk/student/Michael_Sort_student20.jpg"
                it[question]= "Hvem er \"Michael Sort\""
                it[points] = 300
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "http://team-sort.dk/student/Michael_Sort_student19.jpg"
                it[question]= "Hvem er \"Michael Sort\""
                it[points] = 400
                it[categoryId] = 5
                it[done] = false
            }
            Answers.insert {
                it[answer]= "https://images.bonnier.cloud/files/ill/production/2018/10/13214753/michael-jackson-til-body.jpg"
                it[question]= "Hvem er \"Michael Sort\""
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