dependencyResolutionManagement {
    
    repositories {
        // Compose multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // google and mavenCentral
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
        maven("https://mirrors.tencent.com/nexus/repository/gradle-plugins/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
