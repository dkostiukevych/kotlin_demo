import io.qameta.allure.gradle.AllureExtension
import io.qameta.allure.gradle.AllurePlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.script.lang.kotlin.*

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.2"
    id("io.qameta.allure") version "2.3"
}

apply<AllurePlugin>()

repositories {
    jcenter()
}

dependencies {
    //compile(project(":kirk"))
    compile("com.automation-remarks:kirk:0.7.1")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.2-4")
    compile("org.testng:testng:6.11")
    testCompile("org.testcontainers:testcontainers:1.4.1")
}

val test: Test by tasks

test.testLogging {
    exceptionFormat = TestExceptionFormat.FULL
}

test.useTestNG()

val allure: AllureExtension by extensions
allure.autoconfigure = true
allure.version = "2.1.1"
allure.configuration = "compile"
