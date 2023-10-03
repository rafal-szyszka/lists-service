package com.bpower2.listservice.api.exposed

import com.bpower2.listservice.api.queries.QueriesService
import com.bpower2.listservice.api.queries.data.ListWithData
import com.bpower2.listservice.core.data.models.SimpleList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["v1/queries"])
class QueriesController(
    private val queriesService: QueriesService
) {

    @GetMapping("/struct/{id}")
    fun getList(@PathVariable id: String): ResponseEntity<SimpleList> {
        val optionalList = queriesService.getList(id)
        return if (optionalList.isPresent) {
            ResponseEntity.ok(optionalList.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping(value = ["/data/{id}", "/data/{id}/page/{page}"])
    fun getListWithData(@PathVariable id: String, @PathVariable page: Optional<Int>): ResponseEntity<ListWithData> {
        val optionalList = queriesService.getListWithData(id, page)
        return if (optionalList.isPresent) {
            ResponseEntity.ok(optionalList.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

}