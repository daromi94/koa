plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.daromi.koa.datasource"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":datatypes"))

    implementation(libs.arrow.vector)
    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
