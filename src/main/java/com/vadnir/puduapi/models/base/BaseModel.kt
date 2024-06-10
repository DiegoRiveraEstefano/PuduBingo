package com.vadnir.puduapi.models.base

import java.util.UUID

class BaseModel(
    private val uuid: UUID = UUID.randomUUID()
) {

    private val data: HashMap<String, Any> = hashMapOf()


}