package com.example.elasticsearch.repository

import com.example.elasticsearch.entity.Shop
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import reactor.core.publisher.Flux

/** ShopAdvancedRepository
 * @author Brian
 * @since 2022/11/02
 */
interface ShopAdvancedRepository {

    /** 특정 위치를 기존으로 특정 거리 내에 존재하는 shop을 반환하는 메소드
     * @param geoPoint the center point
     * @param distance the distance
     * @param unit unit of the distance
     */
    fun searchWithIn(geoPoint: GeoPoint, distance: Double, unit: String): Flux<Shop>
}