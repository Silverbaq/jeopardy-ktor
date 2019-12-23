package dk.w4.webapp

import dk.w4.Repository.AnswerRepository
import dk.w4.Repository.TeamRepository
import dk.w4.gameserver.JeopardyServer
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

private const val GAMEANSWER = "/gameanswer"

@Location(GAMEANSWER)
class GameAnswer(val answerId: Int)

@Location(GAMEANSWER)
class GameAnswerGuess

fun Route.gameanswer(answerRepository: AnswerRepository, teamRepository: TeamRepository, jeopardyServer: JeopardyServer){
    get<GameAnswer>{ input ->
        val id = input.answerId
        val answer = answerRepository.getById(input.answerId) ?: throw IllegalArgumentException("There is no answer based on id: $id")
        val teams = teamRepository.getAll()

        call.respond(FreeMarkerContent("gameanswer.ftl", mapOf("answer" to answer, "teams" to teams)))
    }
    post<GameAnswerGuess>{
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        val teamId = params["teamId"] ?: throw IllegalArgumentException("Missing parameter: teamId")
        val answerId = params["answerId"] ?: throw IllegalArgumentException("Missing parameter: answerId")
        val team = teamRepository.getById(teamId) ?: throw IllegalArgumentException("No team found by id $teamId")
        val answer = answerRepository.getById(answerId) ?: throw IllegalArgumentException("No answer found by id $answerId")

        when (action) {
            "correct" -> {
                val points = team.points + answer.points
                teamRepository.updateTeamPoints(teamId.toInt(), points)
                val updatedTeam = teamRepository.getById(teamId) ?: throw NullPointerException("Team not found by id $teamId")
                answerRepository.update(Answer(answer.id, answer.question, answer.answer, answer.points, answer.categoryId, true))
                jeopardyServer.updateTeamPoints(points, updatedTeam, true)
                call.redirect(GameControls())
            }
            "wrong" -> {
                val points = team.points - answer.points
                teamRepository.updateTeamPoints(teamId.toInt(), points)
                val updatedTeam = teamRepository.getById(teamId) ?: throw NullPointerException("Team not found by id $teamId")
                jeopardyServer.updateTeamPoints(points, updatedTeam, false)
                call.redirect(GameAnswer(answerId.toInt()))
            }
        }
    }


}