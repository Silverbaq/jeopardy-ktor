package dk.w4.gameserver

import com.google.gson.Gson
import dk.w4.model.Answer
import dk.w4.model.Category
import dk.w4.model.Team
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import kotlinx.coroutines.channels.ClosedSendChannelException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class JeopardyServer : Jeopardy {
    private val boardClients = ConcurrentHashMap<String, MutableList<WebSocketSession>>()
    private val gameBoard = GameBoard(mutableListOf(), mutableListOf())

    suspend fun clientJoin(name: String, socket: WebSocketSession) {
        val list = boardClients.computeIfAbsent(name) { CopyOnWriteArrayList<WebSocketSession>() }
        list.add(socket)

        socket.send(Frame.Text("Welcome, WebSocket!"))
    }

    suspend fun broadcastMessage(message: String) {
        boardClients.values.forEach {
            it.forEach { socket ->
                try {
                    socket.send(Frame.Text(message))
                } catch (ex: ClosedSendChannelException){
                    it.remove(socket)
                }
            }
        }
    }

    override suspend fun giveTeamPoints(points: Int, team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun takeTeamPoints(points: Int, team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun startRound(categories: List<Category>, teams: List<Team>) {
        gameBoard.teams.clear()
        gameBoard.teams.addAll(teams)
        gameBoard.categories.clear()
        gameBoard.categories.addAll(categories)

        val cmd = Command("BOARD", gameBoard)
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

    override suspend fun selectAnswer(answer: Answer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun hideAnswer(answer: Answer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface ClientData
    data class Command(val cmd: String, val data: ClientData)
    data class GameBoard(val teams: MutableList<Team>, val categories: MutableList<Category>) : ClientData
}