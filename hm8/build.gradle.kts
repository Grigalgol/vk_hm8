plugins {
    java
    kotlin("jvm") version "1.4.31" apply false
    id("nu.studer.jooq") version "6.0.1" apply false
    `kotlin-dsl`
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":jooq_generate"))
    implementation("org.eclipse.jetty:jetty-server:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-client:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-servlets:9.4.33.v20201020")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.flywaydb:flyway-core:9.7.0")
    implementation("org.postgresql:postgresql:42.5.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    implementation("org.jooq:jooq:3.15.4")
    implementation("org.jooq:jooq-codegen:3.15.4")
    implementation("org.jooq:jooq-meta:3.15.4")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.google.inject.extensions:guice-servlet:5.1.0")
    implementation("org.jboss.resteasy:resteasy-guice:4.7.7.Final")
    implementation("org.jboss.resteasy:resteasy-jackson-provider:4.0.0.Beta5")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}



subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("nu.studer.jooq")
    }

    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains:annotations:23.0.0")
        implementation("org.flywaydb:flyway-core:9.7.0")
        implementation("org.postgresql:postgresql:42.5.0")
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.22")
        implementation("org.jooq:jooq:3.15.4")
        implementation("org.jooq:jooq-codegen:3.15.4")
        implementation("org.jooq:jooq-meta:3.15.4")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}