package com.bpower2.listservice.communication.data_service.api

import com.bpower2.listservice.communication.data_service.api.data.PaginatedQueryResult
import com.bpower2.listservice.proql.ProQLQuery
import org.springframework.stereotype.Service

@Service
class DataServiceBridgeService(
    private val dataServiceRestCalls: DataServiceRestCalls
) {

    fun getData(query: ProQLQuery): PaginatedQueryResult {
        return dataServiceRestCalls.getData(query)
    }

}