package com.vadnir.pudubingo.listeners

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.objective.ObjectiveTypes
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent

class PlayerPickUpItem(private val plugin: PuduBingo): Listener {

    @EventHandler
    fun onEntityPickUpItem(event: EntityPickupItemEvent){

        if (event.entity.type != EntityType.PLAYER){
            return
        }
        val eventPlayer: Player = event.entity as Player

        val gameUUID = plugin.playerGameHandler[eventPlayer.uniqueId] ?: return
        val game = plugin.globalGamaManager.games[gameUUID] ?: return

        val player = game.playerManager.getPlayer(eventPlayer.uniqueId) ?: return
        val team = game.teamManager.getPlayerTeam(player.uuid) ?: return
        val objective = game.objectiveManager.getObjective(
            eventPlayer.inventory.itemInMainHand.type.name, ObjectiveTypes.Items) ?: return
        team.getOrAddObjective(objective)?: return

        // todo make message send
    }
}