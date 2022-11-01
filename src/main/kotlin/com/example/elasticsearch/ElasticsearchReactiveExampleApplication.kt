package com.example.elasticsearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElasticsearchReactiveExampleApplication

fun main(args: Array<String>) {
    runApplication<ElasticsearchReactiveExampleApplication>(*args)
}
