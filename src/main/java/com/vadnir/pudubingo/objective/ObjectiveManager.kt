package com.vadnir.pudubingo.objective

class ObjectiveManager(
    private var objectiveList: HashMap<String, Objective> = hashMapOf<String, Objective>()
) {

    private fun hasObjective(objectiveName: String): Boolean {
        return this.objectiveList.containsKey(objectiveName)
    }

    private fun hasObjective(objective: Objective): Boolean {
        return this.objectiveList.containsKey(objective.getName())
    }

    fun getAllObjectives(): List<Objective> {
        return this.objectiveList.values.toList()
    }

    fun getObjectiveByType(objectivesType: ObjectiveTypes): List<Objective> {
        return this.objectiveList.values.filter { it.type == objectivesType }.toList()
    }

    fun getObjective(condition: String, objectiveTypes: ObjectiveTypes): Objective? {
        return this.objectiveList.values.firstOrNull {
            it.type == objectiveTypes && it.condition == condition
        }
    }

    fun getObjective(objectiveName: String): Objective? {
        if(!this.hasObjective(objectiveName)){
            throw Exception("not exist this a Objective with this name")
        }
        return this.objectiveList[objectiveName]

    }

    fun setObjectives(newObjectives: List<Objective>){
        newObjectives.associateByTo(this.objectiveList) { it.getName() }
    }

    fun addObjective(objective: Objective) {
        if(this.hasObjective(objective)){
            throw Exception("this Objective Exist")
        }
        this.objectiveList[objective.getName()] = objective
    }

}