package com.bpower2.listservice.communication.data_service.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class DataServiceRestConfiguration {

    @Value("\${services.locations.data-service.host}")
    lateinit var host: String

    @Value("\${services.locations.data-service.query-endpoint}")
    lateinit var getDataEndpoint: String

    @Value("\${services.locations.data-service.ttl}")
    lateinit var getTtlForRedis: String

}