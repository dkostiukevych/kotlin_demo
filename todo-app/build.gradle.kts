import io.qameta.allure.gradle.AllureExtension
import io.qameta.allure.gradle.AllurePlugin
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.script.lang.kotlin.apply
import org.gradle.script.lang.kotlin.compile
import org.gradle.script.lang.kotlin.dependencies
import org.gradle.script.lang.kotlin.repositories

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.4-2"
    id("io.qameta.allure") version "2.3"
}

apply<AllurePlugin>()

repositories {
    jcenter()
}

dependencies {
    compile("com.automation-remarks:kirk:0.8.3")
    compile("org.testng:testng:6.11")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.4-2")
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
