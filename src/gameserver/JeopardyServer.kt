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

    override suspend fun refresh(teams: List<Team>, categories: List<Category>) {
        //val cmd = Command(BOARD, gameBoard)
        //val json = Gson().toJson(cmd)
        //broadcastMessage(json)
        startRound(categories, teams)
    }

    override suspend fun randomImage() {
        val cmd = Command(IMAGE, EmptyData())
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

    override suspend fun randomVideo() {
        val cmd = Command(VIDEO, EmptyData())
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

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
                } catch (ex: ClosedSendChannelException) {
                    it.remove(socket)
                }
            }
        }
    }

    override suspend fun updateTeamPoints(points: Int, team: Team, givePoints: Boolean) {
        val teamPoints = TeamPointsData(team.name, team.points)
        if (givePoints) {
            val cmd = Command(GIVE_POINTS, teamPoints)
            val json = Gson().toJson(cmd)
            broadcastMessage(json)
        } else {
            val cmd = Command(TAKE_POINTS, teamPoints)
            val json = Gson().toJson(cmd)
            broadcastMessage(json)
        }
    }

    suspend fun showAnswer(){
        val cmd = Command(SHOW_ANSWER, EmptyData())
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

    override suspend fun startRound(categories: List<Category>, teams: List<Team>) {
        gameBoard.teams.clear()
        gameBoard.teams.addAll(teams)
        gameBoard.categories.clear()
        gameBoard.categories.addAll(categories)

        val cmd = Command(BOARD, gameBoard)
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

    override suspend fun selectAnswer(answer: Answer) {
        val answerData = AnswerData(mutableListOf(), answer)
        val cmd = Command(ANSWER, answerData)
        val json = Gson().toJson(cmd)
        broadcastMessage(json)
    }

    override suspend fun hideAnswer(answer: Answer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface ClientData
    data class Command(val cmd: String, val data: ClientData)
    data class GameBoard(val teams: MutableList<Team>, val categories: MutableList<Category>) : ClientData
    data class AnswerData(val teams: MutableList<Team>, val answer: Answer) : ClientData
    data class TeamPointsData(val teamName: String, val points: Int): ClientData
    class EmptyData: ClientData

    companion object {
        private const val BOARD = "BOARD"
        private const val ANSWER = "ANSWER"
        private const val TAKE_POINTS = "TAKE_POINTS"
        private const val GIVE_POINTS = "GIVE_POINTS"
        private const val IMAGE = "IMAGE"
        private const val VIDEO = "VIDEO"
        private const val SHOW_ANSWER = "SHOW_ANSWER"
    }
}