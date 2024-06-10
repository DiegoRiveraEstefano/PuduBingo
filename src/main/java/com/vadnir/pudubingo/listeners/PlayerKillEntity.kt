package com.vadnir.pudubingo.listeners

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.objective.ObjectiveTypes
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class PlayerKillEntity(private val plugin: PuduBingo): Listener {

    @EventHandler
    fun onKillEntity(event: EntityDeathEvent){

        val deathEntity = event.entity ?: return
        val entityKiller = event.entity.killer ?: return

        if (entityKiller.type != EntityType.PLAYER){
            return
        }

        val gameUUID = plugin.playerGameHandler[entityKiller.uniqueId] ?: return
        val game = plugin.globalGamaManager.games[gameUUID] ?: return

        val player = game.playerManager.getPlayer(entityKiller.uniqueId) ?: return
        val team = game.teamManager.getPlayerTeam(player.uuid) ?: return

        val objective = game.objectiveManager.getObjective(
            deathEntity.type.name, ObjectiveTypes.Mobs) ?: return
        team.getOrAddObjective(objective)?: return
    }
}