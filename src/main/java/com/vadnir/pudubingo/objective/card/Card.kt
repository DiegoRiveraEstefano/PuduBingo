package com.vadnir.pudubingo.objective.card

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType
import com.vadnir.pudubingo.objective.Objective
import com.vadnir.pudubingo.player.Player
import org.bukkit.Bukkit
import org.bukkit.Material

data class Card(
    private val namespace: String,
    val objectives: List<Objective>,
    private val advancementApi: UltimateAdvancementAPI
) {

    private var advancementTab: AdvancementTab = advancementApi.createAdvancementTab(namespace)

    val rootAdvancement: RootAdvancement = createRootAdvancement()

    private fun createRootAdvancement(): RootAdvancement {
        return RootAdvancement(this.advancementTab, "root",
            AdvancementDisplay(
                Material.MAP, "Bingo", AdvancementFrameType.CHALLENGE,
                false, false, 0f, 3f,
                "Donde", "Todo Comienza"),
            "textures/block/gray_concrete.png")
    }

    fun loadRootAdvancementToPlayers(players: List<Player>){
        players.forEach {
            val bukkitPlayer: org.bukkit.entity.Player = Bukkit.getPlayer(it.name) ?: return@forEach
            advancementTab.showTab(bukkitPlayer)
            advancementTab.grantRootAdvancement(bukkitPlayer)
        }
    }
}