import com.modrinth.minotaur.dependencies.DependencyType
import com.modrinth.minotaur.dependencies.ModDependency
import dev.deftu.gradle.utils.GameSide

plugins {
    java
    kotlin("jvm")
    id("dev.deftu.gradle.multiversion")
    id("dev.deftu.gradle.tools")
    id("dev.deftu.gradle.tools.resources")
    id("dev.deftu.gradle.tools.bloom")
    id("dev.deftu.gradle.tools.shadow")
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

toolkitReleases {
    modrinth {
        projectId.set("I16j44CK")
        dependencies.add(ModDependency("Ha28R6CL", DependencyType.REQUIRED)) // Fabric Language Kotlin
    }
}

dependencies {
    modImplementation("net.fabricmc:fabric-language-kotlin:${mcData.dependencies.fabric.fabricLanguageKotlinVersion}")

    listOf(
        "binding" to true,
        "lwjgl3" to true,
        "natives-windows" to false,
        "natives-linux" to false,
        "natives-macos" to false
    ).forEach { (module, bundled) ->
        val version = "1.86.11"
        implementation("io.github.spair:imgui-java-$module:$version") {
            exclude(group = "org.lwjgl")
        }

        if (bundled) {
            include("io.github.spair:imgui-java-$module:$version")
        } else {
            shade("io.github.spair:imgui-java-$module:$version")
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
