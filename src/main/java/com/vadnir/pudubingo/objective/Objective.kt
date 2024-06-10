package com.vadnir.pudubingo.objective

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType
import kotlinx.serialization.Serializable
import org.bukkit.Material

@Serializable
data class Objective(
    val type: ObjectiveTypes,
    val condition: String,
    val completeMessage: String,
    val title: String,
    val points: Int,
    val description: String,
    private val cord: Pair<Float, Float>,
    private val itemDisplayName: String,
) {

    private val itemDisplay: Material = Material.getMaterial(this.itemDisplayName)
        ?: throw Exception("Invalid Material Name")

    fun getName(): String {
        return "${this.type}_${condition}".lowercase()
    }

    fun getBaseAdvancement(rootAdvancement: RootAdvancement): BaseAdvancement {
        val advancementDisplay: AdvancementDisplay = AdvancementDisplay(
            this.itemDisplay, this.title, AdvancementFrameType.TASK,
            false, false, cord.first, cord.second,
            description)

        return BaseAdvancement(this.getName(), advancementDisplay, rootAdvancement)
    }
}