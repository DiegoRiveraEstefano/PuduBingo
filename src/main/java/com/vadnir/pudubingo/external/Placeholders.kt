package com.vadnir.pudubingo.external

import com.vadnir.pudubingo.PuduBingo
import io.github.miniplaceholders.api.Expansion
import org.bukkit.entity.Player

class Placeholders(private val plugin: PuduBingo) {

    private lateinit var expansion: Expansion

    init {
       this.expansion = Expansion.builder("pudubingo")
           .filter {  }
           .audiencePlaceholder()
           .build()
    }

}