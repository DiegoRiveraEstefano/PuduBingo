package com.vadnir.pudubingo.game

import com.google.gson.annotations.Expose
import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.objective.Objective
import com.vadnir.pudubingo.objective.ObjectiveManager
import com.vadnir.pudubingo.objective.card.Card
import com.vadnir.pudubingo.player.Player
import com.vadnir.pudubingo.player.PlayerManager
import com.vadnir.pudubingo.runnables.GameTimerRunnable
import com.vadnir.pudubingo.runnables.PlayerLocationRunnable
import com.vadnir.pudubingo.runnables.RunnableManager
import com.vadnir.pudubingo.team.Team
import com.vadnir.pudubingo.team.TeamManager
import java.util.UUID


data class Game(
    val plugin: PuduBingo,
    private val cardSize: Int,
    private val teamSize: Int,
    val gameDuration: Int = 360,
    var teams: ArrayList<Team> = arrayListOf(),
    var players: List<Player> = arrayListOf(),
    val objectives: ArrayList<Objective> = arrayListOf(),
    var state: GameStates = GameStates.Creating,
    val uuid: UUID = UUID.randomUUID(),
) {

    val playerManager: PlayerManager = PlayerManager(players)
    val teamManager: TeamManager = TeamManager()
    val objectiveManager: ObjectiveManager = ObjectiveManager()
    val runnableManager: RunnableManager = RunnableManager(this.plugin)

    private lateinit var card: Card

    lateinit var bingoObjectives: List<Objective>

    private fun changeGameState(newState: GameStates){
        this.state = newState
    }

    fun createBingoCard(){

        this.card = Card("game ${this.uuid}",
            this.objectiveManager.getAllObjectives().shuffled().subList(
                fromIndex = 0, toIndex = this.cardSize
            ),
            this.plugin.advancementApi
        )

        this.bingoObjectives = card.objectives
    }

    private fun createTeams(){
        this.teams.clear()
        var teamId = 0
        this.playerManager.getAllPlayers().chunked(this.teamSize).forEach { playerList ->
            val newTeam = Team(
                id = teamId,
                playerList = arrayListOf(*playerList.toTypedArray())
            )
            this.teams.add(newTeam)
            teamId += 1
        }
    }

    fun saveData(){

    }

    fun startGame(){
        this.state = GameStates.Starting
        this.players.forEach { player ->
            this.plugin.playerGameHandler[player.uuid] = this.uuid
        }
        this.createBingoCard()
        this.card.loadRootAdvancementToPlayers(this.players)

        this.createTeams()

        // run tasks, timer, player location, and game save data
        this.runnableManager.addRunnable(
            GameTimerRunnable(this, this.plugin.server.scheduler),
            "timer",
        )
        this.runnableManager.runTaskTimer("timer", )
        this.state = GameStates.InGame
    }

    fun pauseGame(){

    }

    fun resumeGame(){

    }

    fun cancelGame(){
        this.runnableManager.stopAllRunnable()
        this.players.forEach { player ->
            this.plugin.playerGameHandler.remove(
                player.uuid
            )
        }
        this.state = GameStates.Canceled
    }

    fun endGame(){

        //this is called with the event of game end timer.

        this.runnableManager.stopAllRunnable()

        this.state = GameStates.Ending
        this.players.forEach { player ->
            this.plugin.playerGameHandler.remove(
                player.uuid
            )
        }
        this.state = GameStates.Ended
        //this.saveData()
    }

}