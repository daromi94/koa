plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "core"
include("datatypes")
include("datasource")
include("logical-plan")
