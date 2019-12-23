package dk.w4.gameserver

import dk.w4.model.Answer
import dk.w4.model.Category
import dk.w4.model.Team

interface Jeopardy {
    suspend fun updateTeamPoints(points: Int, team: Team, givePoints: Boolean)
    suspend fun startRound(categories: List<Category>, teams: List<Team>)
    suspend fun selectAnswer(answer: Answer)
    suspend fun hideAnswer(answer: Answer)
    suspend fun refresh(teams: List<Team>, categories: List<Category>)
}