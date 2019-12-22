package dk.w4.webapp

import dk.w4.Repository.CategoryRepository
import dk.w4.Repository.TeamRepository
import dk.w4.gameserver.Jeopardy
import dk.w4.redirect
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route

const val GAMECONTROLS = "/gamecontrols"

@Location(GAMECONTROLS)
class GameControls

fun Route.gamecontrols(teamDB: TeamRepository, categoryRepository: CategoryRepository, jeopardyServer: Jeopardy) {
    get<GameControls> {
        call.respond(FreeMarkerContent("gamecontrols.ftl", null))
    }
    post<GameControls> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "refresh" -> {
                val teams = teamDB.getAll()
                val categories = categoryRepository.getAll()
                jeopardyServer.refresh()
                call.redirect(GameControls())
            }
        }
    }
}