import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

val coroutineVersion = "1.6.3"
val mockkVersion = "1.10.3"
val kotestVersion = "5.3.2"

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

    // Kotlin Coroutines
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")

    // Test Implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // mockk
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:$kotestVersion") // for kotest property test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

    // Annotation Processing Tool
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Spring Data elasticsearch
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
