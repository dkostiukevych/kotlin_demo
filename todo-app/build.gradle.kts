import io.qameta.allure.gradle.AllureExtension
import io.qameta.allure.gradle.AllurePlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.repositories
import org.gradle.script.lang.kotlin.*

val kirkVersion by project
val testNgVersion by project

plugins {
    kotlin("jvm")
    id("io.qameta.allure") version "2.4"
}

apply<AllurePlugin>()

repositories {
    jcenter()
}

dependencies {
    compile("com.automation-remarks:kirk:$kirkVersion")
    compile("org.testng:testng:$testNgVersion")
    compile(kotlin("stdlib-jre8"))
}

val test: Test by tasks

test.testLogging {
    exceptionFormat = TestExceptionFormat.FULL
}

test.useTestNG()
test.ignoreFailures = true

val allure: AllureExtension by extensions
allure.autoconfigure = true
allure.version = "2.3.5"
allure.configuration = "compile"

