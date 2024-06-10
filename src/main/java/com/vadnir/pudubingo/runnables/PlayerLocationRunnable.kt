package com.vadnir.pudubingo.runnables

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.objective.ObjectiveTypes
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class PlayerLocationRunnable(private val plugin: PuduBingo): BukkitRunnable() {

    override fun run() {

        for(uuid in this.plugin.playerGameHandler.values) {
            val bukkitPlayer: Player = Bukkit.getPlayer(uuid) ?: continue

            val gameUUID = plugin.playerGameHandler[bukkitPlayer.uniqueId] ?: return
            val game = plugin.globalGamaManager.games[gameUUID] ?: return

            val player = game.playerManager.getPlayer(bukkitPlayer.uniqueId) ?: return
            val team = game.teamManager.getPlayerTeam(player.uuid) ?: return

            val objective = game.objectiveManager.getObjective(
                bukkitPlayer.location.block.biome.name, ObjectiveTypes.Biomes) ?: return
            team.getOrAddObjective(objective)?: return
        }
    }
}