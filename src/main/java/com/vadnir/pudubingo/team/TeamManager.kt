package com.vadnir.pudubingo.team

import com.vadnir.pudubingo.player.Player
import java.util.UUID

class TeamManager(
    private val teamList: List<Team> = arrayListOf()
) {

    var teams: HashMap<Int, Team> = run {
        val temp = hashMapOf<Int, Team>()
        temp.putAll(teamList.map { team: Team -> Pair(team.id, team) })
        temp
    }


    fun getPlayerTeam(player: Player): Team?{
        val filteredTeams = this.teams.values.filter {
            it.hasPlayer(player.uuid)
        }
        if(filteredTeams.isEmpty()){
            return null
        }
        return filteredTeams.first()
    }

    fun getPlayerTeam(playerUUID: UUID): Team?{
        val filteredTeams = this.teams.values.filter {
            it.hasPlayer(playerUUID)
        }
        if(filteredTeams.isEmpty()){
            return null
        }
        return filteredTeams.first()
    }

}