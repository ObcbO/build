plugins {
    id("dev.architectury.loom") version Versions.Mod.architecuryLoom
    id("architectury-plugin") version Versions.Mod.architecturyPlugin
    id("io.github.juuxel.loom-quiltflower") version Versions.Mod.loomQuiltflower
}

dependencies {
    shadedApi(project(":common:implementation:base"))
    
    compileOnly("net.fabricmc:sponge-mixin:${Versions.Mod.mixin}")
    annotationProcessor("net.fabricmc:sponge-mixin:${Versions.Mod.mixin}")
    annotationProcessor("dev.architectury:architectury-loom:${Versions.Mod.architecuryLoom}")
    
    implementation(project(path = ":platforms:mixin-common", configuration = "namedElements")) { isTransitive = false }
    
    minecraft("com.mojang:minecraft:${Versions.Mod.minecraft}")
    mappings("net.fabricmc:yarn:${Versions.Mod.yarn}:v2")
}

loom {
    accessWidenerPath.set(project(":platforms:mixin-common").file("src/main/resources/terra.accesswidener"))
    
    mixin {
        defaultRefmapName.set("terra.lifecycle.refmap.json")
    }
}

tasks {
    compileJava {
        options.release.set(16)
    }
    
    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
    }
}

architectury {
    common("fabric", "quilt")
    minecraft = Versions.Mod.minecraft
}