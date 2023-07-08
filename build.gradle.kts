val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.openjfx.javafxplugin") version "0.0.13"
    jacoco
}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

javafx {
    version = "17"
}

group = "com.img_processor"
version = "0.0.1"

application {
    mainClass.set("com.imageprocessor.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true") // enables dev mode

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.sksamuel.scrimage:scrimage-core:4.0.24")
    implementation("com.sksamuel.scrimage:scrimage-filters:4.0.24")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("io.mockk:mockk:1.13.4")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}

detekt {
    autoCorrect = true
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // include("**/special/package/**") // only analyze a sub package inside src/main/kotlin
    exclude("**/com/imageprocessor/imagelibrary/**") // but exclude the library for Sketch package
}

tasks.jacocoTestReport {
    reports {
        csv.required.set(true)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}