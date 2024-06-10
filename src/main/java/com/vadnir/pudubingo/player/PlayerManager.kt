package com.vadnir.pudubingo.player

import org.bukkit.Bukkit
import java.util.UUID

class PlayerManager(
    playerList: List<Player> = arrayListOf()
) {

    private val players: HashMap<UUID, Player> = hashMapOf()

    val playerStorage: PlayerStorage = PlayerStorage()

    init {
        this.players.putAll(
            playerList.map { Pair<UUID, Player>(it.uuid, it) }
        )
    }

    private fun hasPlayer(playerIdentifier: String): Boolean{
        return this.players.containsKey(
            Bukkit.getPlayer(playerIdentifier)?.uniqueId
        )
    }

    private fun hasPlayer(playerIdentifier: UUID): Boolean{
        return this.players.containsKey(playerIdentifier)
    }

    fun getAllPlayers(): List<Player> {
        return this.players.values.toList()
    }

    fun getPlayer(playerName: String): Player? {
        val player = this.players[Bukkit.getPlayer(playerName)?.uniqueId]
            ?: return null
        return player
    }

    fun getPlayer(playerUUID: UUID): Player? {
        val player = this.players[playerUUID]
            ?: return null
        return player
    }

    fun getOrCreatePlayer(playerUUID: UUID): Player? {

        // todo this hav to read from the data source for a player data or in ram to load player

        val player = this.getPlayer(playerUUID)
        if(player != null){
          return player
        }
        this.players[playerUUID] = Player(playerUUID)
        return this.players[playerUUID] ?: return null
    }

    fun addPlayer(player: Player){
        if(hasPlayer(player.uuid)){
            throw Exception("This player has a Data")
        }
        this.players[player.uuid] = player
    }

    fun removePlayer(playerUUID: UUID) {
        if(!hasPlayer(playerUUID)){
            throw Exception("This player not has a Data")
        }
        this.players.remove(playerUUID)
    }
}