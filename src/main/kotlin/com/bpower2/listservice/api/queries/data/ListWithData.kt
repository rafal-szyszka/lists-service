package com.bpower2.listservice.api.queries.data

import com.bpower2.listservice.communication.data_service.api.data.PaginatedQueryResult
import com.bpower2.listservice.core.data.models.Column
import com.bpower2.listservice.core.data.models.SimpleList
import java.util.*

data class ListWithData(
    var meta: ListMetadata,
    var columns: List<Column>,
    var data: List<Map<String, Any>>,
) {

    companion object {
        fun from(data: PaginatedQueryResult, list: SimpleList): ListWithData {
            return ListWithData(
                meta = ListMetadata(
                    id = list.id!!,
                    totalElements = data.totalElements,
                    totalPages = data.totalPages,
                    numberOfElements = data.numberOfElements,
                    pageSize = data.pageSize,
                    pageNumber = data.pageNumber,
                ),
                data = mapContent(data.content, list.columns!!),
                columns = list.columns!!
            )
        }

        private fun mapContent(content: List<Map<String, Any>>, columns: List<Column>): List<Map<String, Any>> {
            return content.map {
                it.mapKeys { (key, _) -> findLabelForProperty(key, columns) }
            }
        }

        private fun findLabelForProperty(property: String, columns: List<Column>): String {
            return columns.findLast {
                it.property == property
            }?.label ?: property
        }
    }

}

data class ListMetadata(
    var id: String,
    var totalElements: Int,
    var totalPages: Int,
    var numberOfElements: Int,
    var pageSize: Int,
    var pageNumber: Optional<Int>,
) {}