package dk.w4.webapp

import dk.w4.Repository.AnswerRepository
import dk.w4.Repository.CategoryRepository
import dk.w4.Repository.TeamRepository
import dk.w4.gameserver.Jeopardy
import dk.w4.model.Answer
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

fun Route.gamecontrols(
    teamDB: TeamRepository,
    categoryRepository: CategoryRepository,
    answerRepository: AnswerRepository,
    jeopardyServer: Jeopardy
) {
    get<GameControls> {
        val categories = categoryRepository.getAll()
        call.respond(FreeMarkerContent("gamecontrols.ftl", mapOf("categories" to categories)))
    }
    post<GameControls> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "refresh" -> {
                val teams = teamDB.getAll()
                val categories = categoryRepository.getAll()
                jeopardyServer.refresh(teams, categories)
                call.redirect(GameControls())
            }
            "answer" -> {
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: Id")
                val answer = answerRepository.getById(id) ?: throw IllegalArgumentException("No answer found by id $id")
                jeopardyServer.selectAnswer(answer)
                call.redirect(GameAnswer(id.toInt()))
            }
            "random" -> {
                jeopardyServer.randomImage()
                call.redirect(GameControls())
            }
        }
    }
}