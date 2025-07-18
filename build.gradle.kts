plugins {
    java
    id("io.freefair.lombok") version "6.6.2"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "io.axiomatics.cr.demo.spring"
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
