package com.vadnir.pudubingo

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI
import com.vadnir.pudubingo.game.GameManager
import com.vadnir.pudubingo.objective.ObjectiveManager
import com.vadnir.pudubingo.player.Player
import com.vadnir.pudubingo.player.PlayerManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*


class PuduBingo : JavaPlugin() {

    private lateinit var main: AdvancementMain
    lateinit var advancementApi: UltimateAdvancementAPI

    val globalPlayerManager: PlayerManager = PlayerManager()
    val globalObjectiveManager: ObjectiveManager = ObjectiveManager()
    val globalGamaManager: GameManager = GameManager()

    // first uuid is reference to player and the second to the game uuid
    val playerGameHandler: HashMap<UUID, UUID> = hashMapOf()

    override fun onLoad() {
        super.onLoad()

        main = AdvancementMain(this);
        main.load()
    }

    override fun onEnable() {

        main.enableSQLite(File(dataFolder, "database.db"))
        advancementApi = UltimateAdvancementAPI.getInstance(this)

    }

    override fun onDisable() {
        main.disable()
        stopTasks()
    }


    fun getPlayerFilterByPermission(permission: String): List<Player>{
        return this.globalPlayerManager.getAllPlayers().filter {
            player: Player ->
            Bukkit.getPlayer(player.uuid)?.hasPermission(permission) ?: false
        }.toList()
    }

    private fun stopTasks(){
        this.globalGamaManager.stopGames(force = false)
    }

}
