package com.example.elasticsearch.confifg

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext

/** Configuration class to use elasticsearch reactive
 * @author Brian
 * @since 2022/11/02
 */
@Configuration
class ElasticsearchConfig : AbstractReactiveElasticsearchConfiguration() {

    // Configure the client to use.
    // This can be done by ReactiveRestClients or directly via DefaultReactiveElasticsearchClient
    @Bean
    override fun reactiveElasticsearchClient(): ReactiveElasticsearchClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()

        return ReactiveRestClients.create(clientConfiguration)
    }

    // Set up the ElasticsearchConverter used for domain type mapping utilizing metadata provided by the mapping context
    @Bean
    fun elasticsearchConverter(): ElasticsearchConverter {
        return MappingElasticsearchConverter(elasticsearchMappingContext())
    }

    // The Elasticsearch specific mapping context for domain type metadata
    @Bean
    fun elasticsearchMappingContext(): SimpleElasticsearchMappingContext {
        return SimpleElasticsearchMappingContext()
    }

    // The actual template based on the client and conversion infrastructure
    @Bean
    fun reactiveElasticsearchOperations(): ReactiveElasticsearchOperations {
        return ReactiveElasticsearchTemplate(reactiveElasticsearchClient(), elasticsearchConverter())
    }

}