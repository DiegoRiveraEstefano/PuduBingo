package com.vadnir.pudubingo.team

import com.vadnir.pudubingo.objective.Objective
import com.vadnir.pudubingo.player.Player
import com.vadnir.pudubingo.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Team(
    val id: Int,
    var points: Int = 0,
    val color: String = "",
    private val playerList: ArrayList<Player> = arrayListOf(),
    private val objectiveList: ArrayList<Objective> = arrayListOf()
){

    var players: HashMap<@Serializable(with = UUIDSerializer::class) UUID, Player> = this.setPlayerList(this.playerList)

    var objectives: HashMap<String, Objective> = hashMapOf()

    val teamDisplay: String = "Equipo $id"

    init {
        setAdvancementList(this.objectiveList)
    }

    fun setAdvancementList(newObjectives: List<Objective>): HashMap<String, Objective> {
        this.objectives.clear()
        this.objectives.putAll(
            newObjectives.map { Pair(it.getName(), it) }
        )
        return this.objectives
    }

    fun hasPlayer(playerUUID: UUID): Boolean{
        return this.players.containsKey(playerUUID)
    }

    fun setPlayerList(newPlayerList: List<Player>): HashMap<UUID, Player> {
        this.players.clear()
        this.players.putAll(
            newPlayerList.map { Pair(it.uuid, it) }
        )
        return this.players
    }

    private fun hasObjective(objective: Objective): Boolean {
        return this.objectives.containsKey(objective.getName())
    }

    fun getOrAddPlayer(player: Player): Player? {
        // todo rewrites this
        if (this.hasPlayer(player.uuid)) {
            return this.players[player.uuid]
        }
        this.players[player.uuid] = player
        return this.players[player.uuid]
    }

    fun getOrAddObjective(objective: Objective): Objective? {
        // todo rewrites this
        if (this.hasObjective(objective)) {
            return this.objectives[objective.getName()]
        }

        this.objectives[objective.getName()] = objective
        return this.objectives[objective.getName()]
    }

    fun removePlayer(player: Player): Pair<Boolean, Exception?> {
        if(!this.hasPlayer(player.uuid)){
            return Pair(false, Exception("this team not has this player"))
        }

        this.players.remove(player.uuid)
        return Pair(true, null)
    }

    fun removeObjective(objective: Objective): Pair<Boolean, Exception?> {
        if(!this.hasObjective(objective)){
            return Pair(false ,Exception("this team not has the objective"))
        }
        this.objectives.remove(objective.getName())
        return Pair(true, null)
    }
}
