plugins {
    id("java")
}

group = "net.zhuruoling"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
    maven {
        url = uri("https://libraries.minecraft.net")
    }

    maven {
        url = uri("https://repo.opencollab.dev/maven-releases/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    maven{
        url = uri("https://jcenter.bintray.com/")
    }
}

dependencies {
    implementation("com.github.OhMyMinecraftServer:omms-central:master-SNAPSHOT")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}