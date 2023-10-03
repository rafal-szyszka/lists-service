package com.bpower2.listservice.core.data.repos

import com.bpower2.listservice.core.data.models.SimpleList
import org.springframework.data.mongodb.repository.MongoRepository

interface ListRepository: MongoRepository<SimpleList, String> {
}