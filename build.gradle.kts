// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("jp.gcreate.versionCatalogUpdate")
    id("jp.gcreate.dependencyAnalysis")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

buildscript {
    configurations.all {
        resolutionStrategy.eachDependency {
            when {
                requested.name == "javapoet" -> useVersion("1.13.0")
            }
        }
    }
}