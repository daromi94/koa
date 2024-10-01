plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.daromi.koa.datatypes"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.arrow.memory)
    implementation(libs.arrow.vector)
    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
