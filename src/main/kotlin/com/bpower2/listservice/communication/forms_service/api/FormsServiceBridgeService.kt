package com.bpower2.listservice.communication.forms_service.api

import com.bpower2.listservice.communication.forms_service.api.data.FormDefinition
import org.springframework.stereotype.Service

@Service
class FormsServiceBridgeService(
    private val formsServiceRestCalls: FormsServiceRestCalls
) {

    fun getFormDefinition(formId: String): FormDefinition {
        return formsServiceRestCalls.getFormDefinition(formId)
    }

}