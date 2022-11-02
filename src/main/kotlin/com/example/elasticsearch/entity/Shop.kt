package com.example.elasticsearch.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.GeoPointField
import org.springframework.data.elasticsearch.core.geo.GeoPoint

/** product entity 24분까지 봄
 * @author Brian
 * @since 2022/11/01
 */
@Document(indexName = "shop")
class Shop(
    @Id
    var id: String = "",
    @Field(type = FieldType.Text, name = "name")
    var name: String = "",
    @Field(type = FieldType.Text, name = "description")
    var description: String = "",
    @GeoPointField
    var location: GeoPoint = GeoPoint(0.0, 0.0)
) {

}