package com.bpower2.listservice.api.commands

import com.bpower2.listservice.api.commands.data.ListOptions
import com.bpower2.listservice.communication.forms_service.api.FormsServiceBridgeService
import com.bpower2.listservice.core.data.models.Column
import com.bpower2.listservice.core.data.models.Options
import com.bpower2.listservice.core.data.models.SimpleList
import com.bpower2.listservice.core.data.repos.ListRepository
import com.bpower2.listservice.proql.ProQLQuery
import org.springframework.stereotype.Service

@Service
class CommandsService(
    private val listRepository: ListRepository,
    private val formsServiceBridgeService: FormsServiceBridgeService
) {

    fun createList(listOptions: ListOptions): SimpleList {
        val formDefinition = formsServiceBridgeService.getFormDefinition(listOptions.formId)

        val list = SimpleList(
            name = listOptions.name,
            presenterFormId = formDefinition.id,
            columns = formDefinition.fields?.map {
                Column(
                    techName = it.id!!,
                    label = it.label!!,
                    property = it.property!!,
                    options = Options(visible = true, sumPageValues = false, sumAllValues = false),
                )
            },
            query = ProQLQuery(type = formDefinition.formType!!, size = listOptions.pageSize)
        )

        return listRepository.save(list)
    }

}