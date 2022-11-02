package com.example.elasticsearch.repository

import com.example.elasticsearch.entity.Shop
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * @author Brian
 * @since 2022/11/02
 */
@Repository
interface ShopRepository : ReactiveElasticsearchRepository<Shop, String>, ShopAdvancedRepository {
    fun findAllByName(name: String): Flux<Shop>
}