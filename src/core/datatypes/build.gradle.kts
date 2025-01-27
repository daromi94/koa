plugins { alias(libs.plugins.kotlin.jvm) }

group = "com.daromi.koa.core.datatypes"
version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(libs.arrow.vector)

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
