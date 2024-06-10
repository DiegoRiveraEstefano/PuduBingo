package com.vadnir.pudubingo.player

import com.vadnir.pudubingo.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import java.util.UUID

@Serializable
data class Player(
    @Serializable(with = UUIDSerializer::class) val uuid: UUID,
    val points: Int = 0
) {

    var name: String = Bukkit.getPlayer(this.uuid)?.name ?: throw Exception("not exist player with this uuid")
}
