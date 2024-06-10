package com.vadnir.pudubingo.external

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType
import com.vadnir.pudubingo.PuduBingo
import org.bukkit.Material
import java.io.File


class Advancements(private val plugin: PuduBingo) {

    private var advancementApi: UltimateAdvancementAPI = this.plugin.advancementApi

    private var advancementTab: AdvancementTab = advancementApi.createAdvancementTab("bingo")

    private fun createRootAdvancement(): RootAdvancement {
        return RootAdvancement(this.advancementTab, "root",
            AdvancementDisplay(
                Material.MAP, "Bingo", AdvancementFrameType.CHALLENGE,
                false, false, 0f, 3f,
                "Donde", "Todo Comienza"),
            "textures/block/gray_concrete.png")
    }

}