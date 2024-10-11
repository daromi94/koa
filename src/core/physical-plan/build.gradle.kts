plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.daromi.koa.physical-plan"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
