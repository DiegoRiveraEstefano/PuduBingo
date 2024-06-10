package com.vadnir.puduapi.models.player

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class BasePlayerData(
    private val uuid: UUID = UUID.randomUUID()
) {
}