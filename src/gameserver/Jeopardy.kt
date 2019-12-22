package dk.w4.gameserver

import dk.w4.model.Answer
import dk.w4.model.Team

interface Jeopardy {
    suspend fun giveTeamPoints(points: Int, team: Team)
    suspend fun takeTeamPoints(points: Int, team: Team)
    suspend fun startRound()
    suspend fun selectAnswer(answer: Answer)
    suspend fun hideAnswer(answer: Answer)
}