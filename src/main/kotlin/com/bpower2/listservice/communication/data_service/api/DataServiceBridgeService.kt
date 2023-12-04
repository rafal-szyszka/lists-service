package com.bpower2.listservice.communication.data_service.api

import com.bpower2.listservice.proql.ProQLQuery
import org.springframework.stereotype.Service

@Service
class DataServiceBridgeService(
    private val dataServiceRestCalls: DataServiceRestCalls
) {

    fun getData(query: ProQLQuery): List<Map<String, Any>> {
        return dataServiceRestCalls.getData(query)
    }

}