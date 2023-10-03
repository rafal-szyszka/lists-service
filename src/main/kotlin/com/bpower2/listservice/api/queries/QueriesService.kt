package com.bpower2.listservice.api.queries

import com.bpower2.listservice.api.queries.data.ListWithData
import com.bpower2.listservice.communication.data_service.api.DataServiceBridgeService
import com.bpower2.listservice.core.data.models.SimpleList
import com.bpower2.listservice.core.data.repos.ListRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class QueriesService(
    private val listRepository: ListRepository,
    private val dataServiceBridgeService: DataServiceBridgeService
) {

    fun getList(id: String): Optional<SimpleList> {
        return listRepository.findById(id)
    }

    fun getListWithData(id: String, page: Optional<Int>): Optional<ListWithData> {
        val list = listRepository.findById(id)
        if (list.isEmpty) return Optional.empty()

        val query = list.get().query!!

        page.ifPresent { query.page = page.get() }

        val data = dataServiceBridgeService.getData(query)

        return Optional.of(
            ListWithData.from(data, list.get())
        )
    }

}