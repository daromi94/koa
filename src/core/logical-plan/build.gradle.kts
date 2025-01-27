plugins { alias(libs.plugins.kotlin.jvm) }

group = "com.daromi.koa.core.logical-plan"
version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(project(":datatypes"))
    implementation(project(":datasource"))

    implementation(libs.arrow.vector)

    testImplementation(libs.kotlin.test)
}

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
