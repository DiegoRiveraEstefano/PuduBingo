package com.vadnir.pudubingo.runnables

import com.vadnir.pudubingo.PuduBingo
import com.vadnir.pudubingo.game.Game
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask

class GameTimerRunnable(private val game: Game, private val scheduler: BukkitScheduler): BukkitRunnable() {

    var actualTime = 0
    val timerDuration = game.gameDuration
    lateinit var task: BukkitTask

    override fun run() {
        actualTime++
        if (actualTime >= timerDuration){
            game.endGame()
        }
    }

    fun stop(){

    }
}