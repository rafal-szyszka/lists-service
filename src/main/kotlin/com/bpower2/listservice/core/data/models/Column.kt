package com.bpower2.listservice.core.data.models

import com.fasterxml.jackson.annotation.JsonIgnore

data class Column(
    var techName: String,
    var label: String,
    var options: Options,
    @JsonIgnore
    var property: String,
) {}

data class Options(
    var visible: Boolean,
    var sumPageValues: Boolean,
    var sumAllValues: Boolean,
)