package com.bpower2.listservice.communication.forms_service.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class FormDefinition(
    var id: String? = null,
    var name: String? = null,
    var formType: String? = null,
    var fields: List<FormFieldDefinition>? = null,
    var isPrimitive: Boolean? = null,
) {
    @JsonProperty("type")
    fun unpackFormType(type: Map<String, Any>) {
        formType = "${(type["module"] as String)}.${(type["name"] as String)}"
        isPrimitive = type["primitive"] as Boolean
    }
}

data class FormFieldDefinition(
    var id: String? = null,
    var label: String? = null,
    var persistenceData: PersistentData? = null,
)

data class PersistentData(
    var name: String? = null,
    var type: Type? = null,

)
data class Type(
    var module: String? = null,
    var name: String? = null,
    var primitive: Boolean? = null,
)
