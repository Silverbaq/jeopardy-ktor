package dk.w4.webapp

import dk.w4.Repository.AnswerRepository
import dk.w4.redirect
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.routing.Route

const val ANSWER = "/answer"

@Location(ANSWER)
class Answer

fun Route.answer(answerRepository: AnswerRepository) {
    post<Answer> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "add" -> {
                val answer = params["answer"] ?: throw IllegalArgumentException("Missing parameter: Answer")
                val question = params["question"] ?: throw IllegalArgumentException("Missing parameter: Question")
                val points = params["points"] ?: throw IllegalArgumentException("Missing parameter: Points")
                val categoryId =
                    params["categoryId"] ?: throw IllegalArgumentException("Missing parameter: CategoryId")
                answerRepository.add(
                    dk.w4.model.Answer(
                        id = 0,
                        question = question,
                        answer = answer,
                        points = points.toInt(),
                        categoryId = categoryId.toInt(),
                        done = false
                    )
                )
                call.redirect(Category(categoryId.toInt()))
            }
        }
    }
}