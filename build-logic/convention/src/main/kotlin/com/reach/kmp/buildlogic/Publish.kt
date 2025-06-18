/*
 * Copyright 2025 Reach Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

private const val AAR_VERSION = "0.0.1-local"

internal fun Project.configurePublish() {
    with(pluginManager) {
        apply("maven-publish")
    }

    afterEvaluate {
        configurePublishing()
    }
}

private fun Project.configurePublishing() {
    configure<PublishingExtension> {
        publications {
            withType<MavenPublication> {
                pom {
                    name.set("")
                    description.set("")
                    url.set("https://github.com/Reach2027/Kmp")

                    licenses {
                        license {
                            name.set("The Apache Software License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    scm {
                        url.set("https://github.com/Reach2027/Kmp")
                        connection.set("scm:git:https://github.com/Reach2027/Kmp.git")
                    }

                    developers {
                        developer {
                            name.set("Reach")
                            email.set("Reach2027@outlook.com")
                        }
                    }
                }

                groupId = "com.reach.kmp"
                artifactId = if ("shared" == project.name) {
                    project.name
                } else {
                    project.parent?.name + "-" + project.name
                }
                version = AAR_VERSION
            }
        }

        repositories {
            maven {
                name = "Local"
                url = uri(rootProject.layout.buildDirectory.file("local").get().asFile.path)
            }
        }
    }
}
