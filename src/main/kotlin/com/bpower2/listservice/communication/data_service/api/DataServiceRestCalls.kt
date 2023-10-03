package com.bpower2.listservice.communication.data_service.api

import com.bpower2.listservice.communication.data_service.api.config.DataServiceRestConfiguration
import com.bpower2.listservice.communication.data_service.api.data.PaginatedQueryResult
import com.bpower2.listservice.proql.ProQLQuery
import com.fasterxml.jackson.databind.DeserializationFeature
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component

@Component
class DataServiceRestCalls(
    private val config: DataServiceRestConfiguration
) {

    private var httpClient: HttpClient = createClient()

    fun getData(query: ProQLQuery): PaginatedQueryResult = runBlocking{
        return@runBlocking httpClient.post(config.host) {
            url {
                appendPathSegments(config.getDataPaginatedEndpoint)
            }
            setBody(query)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body<PaginatedQueryResult>()

    }

    private fun createClient(): HttpClient {
        return HttpClient(CIO) {
            install(DefaultRequest)
            install(Logging)
            install(ContentNegotiation) {
                jackson {
                    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                }
            }
        }
    }

}