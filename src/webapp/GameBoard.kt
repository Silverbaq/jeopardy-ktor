package dk.w4.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

const val GAMEBOARD = "/"

@Location(GAMEBOARD)
class GameBoard

fun Route.gameboard(){
    get<GameBoard> {
        call.respond(FreeMarkerContent("index.ftl", null))
    }
}