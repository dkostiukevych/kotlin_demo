import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.script.lang.kotlin.*

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.2"
}

repositories {
    jcenter()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.2-4")
    compile("org.assertj:assertj-core:3.8.0")
    compile("org.testng:testng:6.11")
    compile(group = "com.codeborne", name = "selenide", version = "4.4.3")
    compile(group = "io.github.bonigarcia", name = "webdrivermanager", version = "1.6.2")
    compile("org.awaitility:awaitility:3.0.0")
    compile("org.hamcrest:hamcrest-all:1.3")
}

val test: Test by tasks

test.testLogging {
    exceptionFormat = TestExceptionFormat.FULL
}

test.useTestNG()