package com.bpower2.listservice.communication.data_service.api.data

import java.util.*

class PaginatedQueryResult(
    var content: List<Map<String, Any>>,
    var totalElements: Int,
    var totalPages: Int,
    var pageSize: Int,
    var pageNumber: Optional<Int>,
    var numberOfElements: Int
) {

}