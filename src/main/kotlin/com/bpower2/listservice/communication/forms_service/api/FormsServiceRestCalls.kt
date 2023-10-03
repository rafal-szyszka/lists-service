package com.bpower2.listservice.communication.forms_service.api

import com.bpower2.listservice.communication.forms_service.api.config.FormsServiceRestConfiguration
import com.bpower2.listservice.communication.forms_service.api.data.FormDefinition
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
class FormsServiceRestCalls(
    private val config: FormsServiceRestConfiguration
) {

    private var httpClient: HttpClient = createClient()

    fun getFormDefinition(formId: String): FormDefinition = runBlocking {
        return@runBlocking httpClient.get(config.host) {
            url {
                appendPathSegments(config.formStructureEndpoint.replace("{id}", formId))
            }
        }.body<FormDefinition>()
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