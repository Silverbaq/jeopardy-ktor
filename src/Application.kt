package dk.w4

import dk.w4.Repository.*
import dk.w4.gameserver.JeopardyServer
import dk.w4.webapp.*
import io.ktor.application.*
import io.ktor.routing.*
import freemarker.cache.*
import io.ktor.freemarker.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import io.ktor.response.respondRedirect
import java.time.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val jeopardyServer = JeopardyServer()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    install(Locations)

    install(io.ktor.websocket.WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    DatabaseFactory.init()

    val teamRepository = TeamRepository()
    val categoryRepository = CategoryRepository()
    val answerRepository = AnswerRepository()
    val finalsRoundRepository = FinalsRoundRepository()

    routing {
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }

        gameboard()
        gamesetup(teamRepository, categoryRepository, jeopardyServer)
        teams(teamRepository)
        categories(categoryRepository)
        answer(answerRepository)
        gamecontrols(teamRepository, categoryRepository, answerRepository, finalsRoundRepository, jeopardyServer)
        gameanswer(answerRepository, teamRepository, jeopardyServer)

        webSocket("/ws") {
            //send(Frame.Text("Hi from server"))
            jeopardyServer.clientJoin("1", this)
            while (true) {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    jeopardyServer.broadcastMessage(frame.readText())
                }
            }
        }
    }
}

suspend fun ApplicationCall.redirect(location: Any) {
    respondRedirect(application.locations.href(location))
}