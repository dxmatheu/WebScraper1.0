plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("it.skrape:skrapeit:1.2.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}