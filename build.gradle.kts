import io.qameta.allure.gradle.AllureExtension
import io.qameta.allure.gradle.AllurePlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.script.lang.kotlin.*

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.2"
    id("io.qameta.allure") version "2.3"
    java
}

apply<AllurePlugin>()

repositories {
    mavenLocal()
    jcenter()
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    //compile(project(":kirk"))
    compile("com.github.jkcclemens:khttp:0.1.0")
    compile("com.automation-remarks:kirk:0.7.4")
    compileOnly("org.projectlombok:lombok:1.16.18")
    //compile(fileTree("libs"))
    compile("com.codeborne:selenide:4.5.1")

    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.2-4")
    compile("org.testng:testng:6.11")
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
