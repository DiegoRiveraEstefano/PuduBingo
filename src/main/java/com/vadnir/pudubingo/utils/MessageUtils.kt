package com.vadnir.pudubingo.utils

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.player.Player
import com.vadnir.pudubingo.team.Team
import io.github.miniplaceholders.api.MiniPlaceholders
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.title.TitlePart
import org.bukkit.Bukkit


class MessageUtils(private val plugin: PuduBingo) {

    private val bukkitAudiences: BukkitAudiences = BukkitAudiences.create(this.plugin)

    private val miniMessage: MiniMessage = MiniMessage.miniMessage()

     fun dispatchMessage(player: org.bukkit.entity.Player, message: String){
        val resolver = MiniPlaceholders.getAudiencePlaceholders(player)
        this.bukkitAudiences.player(player)
            .sendMessage(this.miniMessage.deserialize(message, resolver)
        )
    }

    fun dispatchMessage(player: Player, message: String){
        val bukkitPlayer = Bukkit.getPlayer(player.uuid) ?: throw Exception("this player not exist")
        this.dispatchMessage(bukkitPlayer, message)
    }

    fun dispatchMessage(players: List<Player>, message: String){
        players.forEach {
            this.dispatchMessage(it, message)
        }
    }

    fun dispatchMessage(players: List<org.bukkit.entity.Player>, message: String){
        players.forEach {
            this.dispatchMessage(it, message)
        }
    }

    fun dispatchMessage(team: Team, message: String){
        this.dispatchMessage(
            team.players.mapNotNull { player -> Bukkit.getPlayer(player) }.toList(), message
        )
    }

    fun dispatchMessage(teams: List<Team>, message: String){
        teams.forEach {
            this.dispatchMessage(it, message)
        }
    }

    fun dispatchMessage(message: String) {
        this.bukkitAudiences.players().sendMessage(this.miniMessage.deserialize(message))
    }

    fun dispatchTitle(message: String) {
        this.bukkitAudiences.players().sendTitlePart(
            TitlePart.TITLE, this.miniMessage.deserialize(message))
    }

    fun dispatchSubTitle(message: String) {
        this.bukkitAudiences.players().sendTitlePart(
            TitlePart.SUBTITLE, this.miniMessage.deserialize(message))
    }

    fun close() {
        this.bukkitAudiences.close()
    }

}