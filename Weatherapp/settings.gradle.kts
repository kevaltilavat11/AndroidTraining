pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {

    versionCatalogs {
        create("libs") {
            library("retrofit", "com.squareup.retrofit2:retrofit:2.9.0")
            library("retrofit.gson", "com.squareup.retrofit2:converter-gson:2.9.0")
            library("gms.maps", "com.google.android.gms:play-services-maps:17.0.1")
            library("androidx.recyclerview", "androidx.recyclerview:recyclerview:1.2.1")
            library("androidx.core.ktx", "androidx.core:core-ktx:1.6.0")
            library("androidx.appcompat", "androidx.appcompat:appcompat:1.3.0")
            library("material", "com.google.android.material:material:1.4.0")
            library("androidx.activity", "androidx.activity:activity-ktx:1.2.3")
            library("androidx.constraintlayout", "androidx.constraintlayout:constraintlayout:2.0.4")
            library("junit", "junit:junit:4.13.2")
            library("androidx.junit", "androidx.test.ext:junit:1.1.3")
            library("androidx.espresso.core", "androidx.test.espresso:espresso-core:3.4.0")
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Weather app"
include(":app")
 