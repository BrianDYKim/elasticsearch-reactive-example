package com.example.elasticsearch.repository

import com.example.elasticsearch.entity.Shop
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * @author Brian
 * @since 2022/11/02
 */
@Repository
class ShopAdvancedRepositoryImpl(
    private val reactiveElasticsearchOperations: ReactiveElasticsearchOperations
) : ShopAdvancedRepository {

    override fun searchWithIn(geoPoint: GeoPoint, distance: Double, unit: String): Flux<Shop> {
        val criteria = Criteria.where("location").within(geoPoint, distance.toString() + unit)
        val query = CriteriaQuery.builder(criteria)
            .withSort(Sort.by(GeoDistanceOrder("location", geoPoint).withUnit(unit))) // 거리의 오름차순으로 검색한다
            .build()

        return reactiveElasticsearchOperations.search(query, Shop::class.java)
            .map { it.content }
    }
}