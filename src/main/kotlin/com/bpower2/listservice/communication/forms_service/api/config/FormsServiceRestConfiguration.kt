package com.bpower2.listservice.communication.forms_service.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class FormsServiceRestConfiguration {

    @Value("\${services.locations.forms-service.host}")
    lateinit var host: String


    @Value("\${services.locations.forms-service.structure-endpoint}")
    lateinit var formStructureEndpoint: String

}
