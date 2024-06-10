package com.vadnir.pudubingo.runnables

import com.vadnir.pudubingo.PuduBingo
import org.bukkit.scheduler.BukkitRunnable

class RunnableManager(private val plugin: PuduBingo) {

    private val tasks: HashMap<String, BukkitRunnable> = hashMapOf()

    fun addRunnable(task: BukkitRunnable, name: String){
        this.tasks[name] = task
    }

    fun runTask(name: String){
        val task = this.tasks[name] ?: return
        task.runTask(this.plugin)
    }

    fun runTaskLater(name: String, delay: Long){
        val task = this.tasks[name] ?: return
        task.runTaskLater(this.plugin, delay)
    }

    fun runTaskTimer(name: String, period: Long, delay: Long){
        val task = this.tasks[name] ?: return
        task.runTaskTimer(this.plugin, delay, period)
    }

    fun runAsyncTaskTimer(name: String, period: Long, delay: Long){
        val task = this.tasks[name] ?: return
        task.runTaskTimerAsynchronously(this.plugin, delay, period)
    }


    fun stopRunnable(name: String){
        val task = this.tasks[name] ?: return
        task.cancel()
    }

    fun stopAllRunnable(){
        this.tasks.values.forEach {
            it.cancel()
        }
    }

}