package com.bpower2.listservice.communication.data_service.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class PaginatedQueryResult(
    var content: List<Map<String, Any>>,
    var totalElements: Int,
    var totalPages: Int,
    var pageSize: Int,
    var pageNumber: Int,
    var numberOfElements: Int
) {

    @JsonProperty("pageable")
    fun unpackPageableData(pageable: Map<String, Any>) {
        pageSize = pageable["pageSize"] as Int
        pageNumber = pageable["pageNumber"] as Int
    }

}