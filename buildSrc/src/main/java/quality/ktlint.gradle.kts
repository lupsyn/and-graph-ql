package quality

val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("org.jlleitschuh.gradle.ktlint:1.1.1")
}

tasks {
    register<JavaExec>("ktlint") {
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args(
            "src/**/*.kt", "--reporter=plain", "--reporter=checkstyle," +
                    "output=${buildDir}/reports/ktlint.xml"
        )
    }

    register<JavaExec>("ktlintFormat") {
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args("-F", "src/**/*.kt")
    }
}

tasks.getByName("check") {
    setDependsOn(listOf(tasks.getByName("ktlint")))
}
