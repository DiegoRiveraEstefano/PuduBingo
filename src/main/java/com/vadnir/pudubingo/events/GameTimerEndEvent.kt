package com.vadnir.pudubingo.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import java.util.UUID

class GameTimerEndEvent(private val gameUUID: UUID): Event() {

    private val handlers: HandlerList = HandlerList()

    fun getGameUUID(): UUID {
        return this.gameUUID
    }

    override fun getHandlers(): HandlerList {
        return this.handlers;
    }

}