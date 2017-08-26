plugins {
    java
}

repositories {
    jcenter()
}


dependencies {
    //compile(project(":kirk"))
    compile("com.github.jkcclemens:khttp:0.1.0")
    compile("com.automation-remarks:kirk:0.8.3")
    compileOnly("org.projectlombok:lombok:1.16.18")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.2-4")
    compile("org.testng:testng:6.11")
}





