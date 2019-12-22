package dk.w4.webapp

import dk.w4.Repository.CategoryRepository
import dk.w4.Repository.TeamRepository
import dk.w4.gameserver.Jeopardy
import dk.w4.model.Category
import dk.w4.model.Team
import dk.w4.redirect
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route

const val GAMESETUP = "/gamesetup"

@Location(GAMESETUP)
class GameSetup

fun Route.gamesetup(teamDB: TeamRepository, categoryRepository: CategoryRepository, jeopardyServer: Jeopardy) {
    get<GameSetup> {
        val teams = teamDB.getAll()
        val categories = categoryRepository.getAll()
        call.respond(FreeMarkerContent("gamesetup.ftl", mapOf("teams" to teams, "categories" to categories)))
    }

    post<GameSetup> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "start" -> {
                val teams = teamDB.getAll()
                val categories = categoryRepository.getAll()
                jeopardyServer.startRound(categories, teams)
            }
        }
    }
}

const val TEAMS = "/teams"

@Location(TEAMS)
class Teams

fun Route.teams(teamDB: TeamRepository) {
    post<Teams> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "delete" -> {
                val id = params["id"] ?: throw java.lang.IllegalArgumentException("Missing parameter: id")
                teamDB.removeById(id)
            }
            "add" -> {
                val name = params["name"] ?: throw IllegalArgumentException("Missing parameter: Name")
                teamDB.add(Team(id = 0, name = name, points = 0))
                call.redirect(GameSetup())
            }
        }
        call.redirect(GameSetup())
    }
}



