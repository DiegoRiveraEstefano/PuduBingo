package com.vadnir.puduapi.models.base

import java.util.UUID

class BaseManager<T>(
    private val initialData: List<T> = listOf()
) {

    private val data: HashMap<UUID, T> = initializeData()


    private fun initializeData(): HashMap<UUID, T> {

    }

    fun addElement(){

    }

    fun removeElement(){

    }

    fun getElement(uuid: UUID){

    }

    fun getAllElements(): List<T>{

    }

    fun

}