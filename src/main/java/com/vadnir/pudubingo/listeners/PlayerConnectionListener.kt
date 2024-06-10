package com.vadnir.pudubingo.listeners

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.player.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.UUID

class PlayerConnectionListener(private val plugin: PuduBingo): Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        // todo enable this this.plugin.getUltimateAdvancementAPI().getApi().updatePlayer(event.player)
        val newPlayer = this.plugin.globalPlayerManager.getOrCreatePlayer(
            event.player.uniqueId
        )
    }

    @EventHandler
    fun onPlayerQuitEvent(event: PlayerQuitEvent) {
        val player: UUID = event.player.uniqueId
        this.plugin.globalPlayerManager.removePlayer(
            player
        )
        this.plugin.playerGameHandler.remove(player)
    }
}