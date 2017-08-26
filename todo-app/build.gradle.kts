version 'unspecified'

buildscript {
    ext.kotlin_version = '1.1.4-2'

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

apply plugin: 'java'
apply plugin: 'kotlin'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile("com.automation-remarks:kirk:0.8.3")
    compile("org.testng:testng:6.11")
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlin_version"
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}