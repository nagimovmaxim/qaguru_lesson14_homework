plugins {
    id("java-library")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation(platform("org.assertj:assertj-bom:3.27.6"))
    testImplementation(platform("io.rest-assured:rest-assured-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:json-schema-validator")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    systemProperties(System.getProperties().map { it.key.toString() to it.value }.toMap())
    testLogging {
        lifecycle {
            events( "started", "skipped", "failed", "standard_error", "standard_out")
            setExceptionFormat( "short")
        }
    }
}