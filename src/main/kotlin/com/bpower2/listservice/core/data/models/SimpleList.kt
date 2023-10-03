package com.bpower2.listservice.core.data.models

import com.bpower2.listservice.proql.ProQLQuery
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
open class SimpleList(

    @Id
    open var id: String? = null,

    open var name: String? = null,

    @JsonIgnore
    open var presenterFormId: String? = null,

    @JsonIgnore
    open var query: ProQLQuery? = null,

    open var columns: List<Column>? = null

) {
}