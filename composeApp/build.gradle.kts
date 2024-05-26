import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    // new
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.sqldelight)

}

kotlin {
    androidTarget {
        androidTarget() // <-- please register this Android target
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }


    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting


        iosMain.dependencies {
            // SqlDelight
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)
        }
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // new
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            // SqlDelight
            implementation(libs.runtime)
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Share data across layer
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.sqlite.bundled)
            implementation(libs.room.runtime)

            api(libs.koin.core)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)

            // SqlDelight
            implementation(libs.runtime)
            implementation(libs.runtime)
            implementation(libs.kotlinx.datetime)

            // Moko MVVM
            implementation(libs.mvvm.core)
            implementation(libs.mvvm.livedata)

            api(libs.koin.core)

        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqlite.driver)
        }
    }
}

android {
    namespace = "kmp.project.composekmm"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "kmp.project.composekmm"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kmp.project.composekmm"
            packageVersion = "1.0.0"
        }
    }
}
room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    implementation(libs.androidx.material3.android)
    ksp(libs.room.compiler)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.androidx.compose)
    implementation(libs.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.google.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.koin.androidx.compose.v320)

    implementation(libs.lifecycle.viewmodel)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)

}
sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.tufelmalik.composekmm")
        }
    }
}
