package dk.w4.webapp

import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.routing.Route

private const val GAMEANSWER = "/gameanswer"

@Location(GAMEANSWER)
class GameAnswer

fun Route.gameanswer(){
    get<GameAnswer>{

    }
}