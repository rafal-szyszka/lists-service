package com.bpower2.listservice.redis

import com.bpower2.listservice.redis.config.RedisConfig
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import redis.clients.jedis.Jedis

@Component
class RedisComponent(
    private val config: RedisConfig
) {

    @Bean
    fun redis(): Jedis {
        return Jedis(config.host, config.port.toInt())
    }
}