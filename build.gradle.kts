import dev.deftu.gradle.utils.GameSide

plugins {
    java
    kotlin("jvm")
    id("dev.deftu.gradle.multiversion")
    id("dev.deftu.gradle.tools")
    id("dev.deftu.gradle.tools.resources")
    id("dev.deftu.gradle.tools.bloom")
    id("dev.deftu.gradle.tools.minecraft.api")
    id("dev.deftu.gradle.tools.minecraft.loom")
    id("dev.deftu.gradle.tools.minecraft.releases")
}

toolkitMultiversion {
    moveBuildsToRootProject.set(true)
}

toolkitLoomApi {
    setupTestClient()
}

toolkitLoomHelper {
    disableRunConfigs(GameSide.SERVER)
}

dependencies {
    modImplementation("net.fabricmc:fabric-language-kotlin:${mcData.dependencies.fabric.fabricLanguageKotlinVersion}")

    listOf(
        "binding",
        "lwjgl3",
        "natives-windows",
        "natives-linux",
        "natives-macos"
    ).forEach { module ->
        implementation("io.github.spair:imgui-java-$module:1.86.11") {
            exclude(group = "org.lwjgl")
        }
    }
}

kotlin {
    explicitApi()
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xjvm-default=all")
        }
    }
}
