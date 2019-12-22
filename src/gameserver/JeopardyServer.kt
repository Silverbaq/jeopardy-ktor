package dk.w4.gameserver

import dk.w4.model.Answer
import dk.w4.model.Team
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class JeopardyServer: Jeopardy {
    val boardClients = ConcurrentHashMap<String, MutableList<WebSocketSession>>()

    suspend fun clientJoin(name: String, socket: WebSocketSession) {
        val list = boardClients.computeIfAbsent(name) { CopyOnWriteArrayList<WebSocketSession>() }
        list.add(socket)

        socket.send(Frame.Text("Welcome, WebSocket!"))
    }

    suspend fun broadcastMessage(message: String) {
        boardClients.values.forEach { it.forEach { it.send(Frame.Text(message)) } }
    }

    override suspend fun giveTeamPoints(points: Int, team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun takeTeamPoints(points: Int, team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun startRound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun selectAnswer(answer: Answer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun hideAnswer(answer: Answer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}