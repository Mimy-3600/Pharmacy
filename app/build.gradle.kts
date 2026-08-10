plugins {
    // On garde UNIQUEMENT la convention de base de ton projet
    id("buildlogic.java-application-conventions")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":utilities"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // Ajout direct de JavaFX sans passer par le plugin capricieux
    val jfxVersion = "21.0.2"
    val os = "linux" // Puisque tu es sur Pop!_OS

    implementation("org.openjfx:javafx-controls:$jfxVersion:$os")
    implementation("org.openjfx:javafx-graphics:$jfxVersion:$os")
    implementation("org.openjfx:javafx-base:$jfxVersion:$os")
    implementation("org.openjfx:javafx-fxml:$jfxVersion:$os")

    implementation("org.postgresql:postgresql:42.7.4")
}

application {
    // TRÈS IMPORTANT : On utilise le Launcher pour éviter les erreurs de modules Java
    mainClass.set("front.main.app.App")
}

tasks.withType<JavaExec> {
    val jfxFiles = configurations.runtimeClasspath.get().files.filter { it.name.contains("javafx") }
    if (jfxFiles.isNotEmpty()) {
        val modulePath = jfxFiles.joinToString(File.pathSeparator) { it.absolutePath }
        jvmArgs("--module-path", modulePath, "--add-modules", "javafx.controls,javafx.fxml,javafx.graphics,javafx.base")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}