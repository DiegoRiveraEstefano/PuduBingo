package com.vadnir.pudubingo.listeners

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.objective.ObjectiveTypes
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class PlayerCompleteAdvancement(private val plugin: PuduBingo): Listener {

    @EventHandler
    fun onPlayerCompleteAdvancement(event: PlayerAdvancementDoneEvent){

        val eventPlayer: Player = event.player
        val gameUUID = plugin.playerGameHandler[eventPlayer.uniqueId] ?: return
        val game = plugin.globalGamaManager.games[gameUUID] ?: return
        val player = game.playerManager.getPlayer(eventPlayer.uniqueId) ?: return
        val team = game.teamManager.getPlayerTeam(player.uuid) ?: return

        val objective = game.objectiveManager.getObjective(
            event.advancement.key.key, ObjectiveTypes.Advancements) ?: return
        team.getOrAddObjective(objective)?: return
    }

}