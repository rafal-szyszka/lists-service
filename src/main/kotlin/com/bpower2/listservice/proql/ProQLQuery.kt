package com.bpower2.listservice.proql

import org.springframework.data.mongodb.core.mapping.Field

open class ProQLQuery(
    open var type: String,
    open var properties: MutableMap<String, Any>? = null,
    open var subQueries: List<ProQLSubQuery>? = null,
    open var page: Int? = null,
    open var size: Int? = null
)

class ProQLSubQuery(
    @Field("subType")
    override var type: String,
    @Field("subProperties")
    override var properties: MutableMap<String, Any>?,
    @Field("_subQueries")
    override var subQueries: List<ProQLSubQuery>?,
    var parentProperty: String,
) : ProQLQuery(type, properties, subQueries)
