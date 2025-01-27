plugins { alias(libs.plugins.kotlin.jvm) }

group = "com.daromi.koa.core.datasource"
version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(project(":datatypes"))

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
