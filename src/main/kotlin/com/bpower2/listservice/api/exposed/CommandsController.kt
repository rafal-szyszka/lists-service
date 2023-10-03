package com.bpower2.listservice.api.exposed

import com.bpower2.listservice.api.commands.CommandsService
import com.bpower2.listservice.api.commands.data.ListOptions
import com.bpower2.listservice.core.data.models.SimpleList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["v1/commands"])
class CommandsController(
    private val commandsService: CommandsService
) {

    @PostMapping
    fun createList(@RequestBody listOptions: ListOptions): SimpleList {
        return commandsService.createList(listOptions)
    }

}