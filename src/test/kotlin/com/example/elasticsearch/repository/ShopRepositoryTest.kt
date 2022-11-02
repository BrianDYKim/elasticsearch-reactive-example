package com.example.elasticsearch.repository

import io.kotest.common.runBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.core.geo.GeoPoint

/**
 * @author Brian
 * @since 2022/11/02
 */
@SpringBootTest
internal class ShopRepositoryTest @Autowired constructor(
    private val shopRepository: ShopRepository
) {

    @Test
    @DisplayName("shopName 부분 검색 테스트")
    fun partialNameSearchTest(): Unit = runBlocking {
        // given
        val partialName = "동해물과"

        // when
        val shopList = shopRepository.findAllByName(partialName).asFlow()
            .toList()

        // then
        shopList.forEach {
            println(it.name)
        }
    }

    @Test
    @DisplayName("POI 반경 검색 테스트")
    fun poiSearchTest(): Unit = runBlocking {
        // given
        val searchPoint = GeoPoint(35.831728870626556, 128.7585397591836) // 영남대학교 위치
        val distance = 10.0
        val unit = "km"

        // when
        val shopList = shopRepository.searchWithIn(searchPoint, distance, unit).asFlow()
            .buffer(capacity = 200)
            .toList()

        // then
        shopList.forEach {
            println(it.name)
        }
    }
}