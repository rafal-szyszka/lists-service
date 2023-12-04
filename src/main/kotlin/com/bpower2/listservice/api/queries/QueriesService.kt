package com.bpower2.listservice.api.queries

import com.bpower2.listservice.api.queries.data.ListWithData
import com.bpower2.listservice.communication.data_service.api.DataServiceBridgeService
import com.bpower2.listservice.communication.data_service.api.config.DataServiceRestConfiguration
import com.bpower2.listservice.communication.data_service.api.data.PaginatedQueryResult
import com.bpower2.listservice.communication.forms_service.api.FormsServiceBridgeService
import com.bpower2.listservice.communication.forms_service.api.data.FormDefinition
import com.bpower2.listservice.core.data.models.SimpleList
import com.bpower2.listservice.core.data.repos.ListRepository
import com.bpower2.listservice.proql.ProQLQuery
import com.bpower2.listservice.proql.ProQLSubQuery
import com.bpower2.listservice.redis.RedisComponent
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.ceil

@Service
class QueriesService(
    private val listRepository: ListRepository,
    private val dataServiceBridgeService: DataServiceBridgeService,
    private val redisComponent: RedisComponent,
    private val dataServiceRestConfiguration: DataServiceRestConfiguration,
    private val formsServiceBridgeService: FormsServiceBridgeService
) {

    fun getList(id: String): Optional<SimpleList> {
        return listRepository.findById(id)
    }

    fun getListWithData(
        id: String,
        page: Optional<Int>,
        filter: Map<String, Any>
    ): Optional<ListWithData> {
        val list = listRepository.findById(id)
        if (list.isEmpty) return Optional.empty()
        var query = list.get().query!!

        var data: List<Map<String, Any>>
        val redisKey = stringBuilder(id, filter)
        if (redisComponent.redis().exists(redisKey)) {
            data = jacksonObjectMapper().readValue(redisComponent.redis().get(redisKey))
        } else {
            val formStructure = formsServiceBridgeService.getFormDefinition(list.get().presenterFormId!!)
            if (filter.isNotEmpty()) {
                filterList(filter, formStructure, query)
            }
            data = dataServiceBridgeService.getData(query)
            redisComponent.redis()
                .setex(
                    redisKey,
                    dataServiceRestConfiguration.getTtlForRedis.toLong(),
                    jacksonObjectMapper().writeValueAsString(data)
                )
        }
        val totalElements = data.size
        val totalPages = ceil(totalElements.toDouble() / query.size!!.toDouble()).toInt()

        page.ifPresent {
            data = paginateList(data, page.get(), query.size!!)
        }

        return Optional.of(
            ListWithData.from(
                PaginatedQueryResult(data, totalElements, totalPages, query.size!!, page, totalElements),
                list.get()
            )
        )
    }

    private fun stringBuilder(
        id: String,
        filter: Map<String, Any>
    ): String {
        val sb = StringBuilder()
        sb.append(id)
        filter.forEach { (key, value) ->
            sb.append(key)
            sb.append(value.toString())
        }
        return sb.toString()
    }

    private fun paginateList(list: List<Map<String, Any>>, page: Int, size: Int): List<Map<String, Any>> {
        if (size <= 0 || page <= 0) return emptyList()
        val fromIndex = (page - 1) * size
        if (list.size <= fromIndex) return emptyList()
        return list.subList(fromIndex, minOf(fromIndex + size, list.size))
    }

    private fun filterList(
        filter: Map<String, Any>,
        formStructure: FormDefinition,
        query: ProQLQuery
    ) {
        val filerToAdd = mutableMapOf<String, Any>()
        filter.forEach { (key, value) ->
            val field = formStructure.fields!!.find { it.id == key }
            val persistentData = field!!.persistenceData!!
            val type = persistentData.type!!
            val name = persistentData.name!!
            if (type.primitive == true) {
                filerToAdd[name] = value
            } else {
                query.subQueries =
                    listOf(ProQLSubQuery(type.module + "." + type.name, mutableMapOf("id" to value), null, name))
            }
        }
        query.properties = filerToAdd
    }
}