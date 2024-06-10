package com.vadnir.pudubingo.game

import java.util.UUID

class GameManager(
    private val gamesList: ArrayList<Game> = arrayListOf()
) {

    val games: HashMap<UUID, Game> = hashMapOf()

    init {
        this.setGames(this.gamesList)
    }

    fun setGames(newGames: List<Game>){
        this.games.clear()
        this.games.putAll(
            newGames.map {
                Pair(it.uuid, it)
            }
        )
    }

    fun stopGames(force: Boolean){
        if (force){
            this.games.values.forEach {
                it.cancelGame()
            }
        } else {
            this.games.values.forEach {
                it.endGame()
            }
        }
    }

}