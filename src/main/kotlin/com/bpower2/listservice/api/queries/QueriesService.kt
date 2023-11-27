package com.bpower2.listservice.api.queries

import com.bpower2.listservice.api.queries.data.ListWithData
import com.bpower2.listservice.communication.data_service.api.DataServiceBridgeService
import com.bpower2.listservice.communication.forms_service.api.FormsServiceBridgeService
import com.bpower2.listservice.communication.forms_service.api.data.FormDefinition
import com.bpower2.listservice.core.data.models.SimpleList
import com.bpower2.listservice.core.data.repos.ListRepository
import com.bpower2.listservice.proql.ProQLQuery
import com.bpower2.listservice.proql.ProQLSubQuery
import org.springframework.stereotype.Service
import java.util.*

@Service
class QueriesService(
    private val listRepository: ListRepository,
    private val dataServiceBridgeService: DataServiceBridgeService,
    private val formsServiceBridgeService: FormsServiceBridgeService
) {

    fun getList(id: String): Optional<SimpleList> {
        return listRepository.findById(id)
    }

    fun getListWithData(
        id: String,
        page: Optional<Int>,
        filter: Optional<HashMap<String, Any>>
    ): Optional<ListWithData> {
        val list = listRepository.findById(id)
        if (list.isEmpty) return Optional.empty()
        var query = list.get().query!!
        val formStructure = formsServiceBridgeService.getFormDefinition(list.get().presenterFormId!!)

        filter.ifPresent {
            filterList(filter, formStructure, query)
        }

        page.ifPresent { query.page = page.get() }
        val data = dataServiceBridgeService.getData(query)

        return Optional.of(
            ListWithData.from(data, list.get())
        )
    }

    private fun filterList(
        filter: Optional<HashMap<String, Any>>,
        formStructure: FormDefinition,
        query: ProQLQuery
    ) {
        filter.get().forEach { (key, value) ->
            val field = formStructure.fields!!.find { it.id == key }
            val persistentData = field!!.persistenceData!!
            val type = persistentData.type!!
            val name = persistentData.name!!
            if (type.primitive == true) {
                query.properties = mutableMapOf(name to value)
            } else {
                query.subQueries =
                    listOf(ProQLSubQuery(type.module + "." + type.name, mutableMapOf("id" to value), null, name))
            }
        }
    }
}