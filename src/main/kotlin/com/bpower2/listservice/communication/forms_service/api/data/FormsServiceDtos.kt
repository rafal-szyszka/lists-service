package com.bpower2.listservice.communication.forms_service.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class FormDefinition(
    var id: String? = null,
    var name: String? = null,
    var formType: String? = null,
    var fields: List<FormFieldDefinition>? = null,
) {
    @JsonProperty("type")
    fun unpackFormType(type: Map<String, Any>) {
        formType = "${(type["module"] as String)}.${(type["name"] as String)}"
    }
}

data class FormFieldDefinition(
    var id: String? = null,
    var label: String? = null,
    var property: String? = null,
) {
    @JsonProperty("persistenceData")
    fun unpackPropertyName(persistenceData: Map<String, Any>) {
        property = persistenceData["name"] as String
    }
}
