package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.vraiment.gradle.jni.task.GenerateJniTask
import com.vraiment.gradle.jni.task.MakeCleanJniTask
import com.vraiment.gradle.jni.task.MakeJniTask

import static com.vraiment.gradle.jni.Util.GENERATE_JNI
import static com.vraiment.gradle.jni.Util.MAKE_CLEAN_JNI
import static com.vraiment.gradle.jni.Util.MAKE_JNI

class JniPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('jni', JniPluginExtension, project)

        project.tasks.create(GENERATE_JNI, GenerateJniTask) {
            doFirst {
                if (!generatedHeadersDir) {
                    generatedHeadersDir = extension.generatedHeadersDir
                }

                if (!jvmHome) {
                    jvmHome = extension.jvmHome
                }

                if (!classpath) {
                    classpath = extension.classpath
                }

                if (!classes) {
                    classes = extension.classes
                }
            }
        }

        project.tasks.create(MAKE_JNI, MakeJniTask) {
            doFirst {
                if (!makeFileDir) {
                    makeFileDir = extension.makeFileDir
                }

                if (!makeOutputDir) {
                    makeOutputDir = extension.makeOutputDir
                }

                if (!jvmHome) {
                    jvmHome = extension.jvmHome
                }
            }
        }

        project.tasks.create(MAKE_CLEAN_JNI, MakeCleanJniTask) {
            doFirst {
                if (!makeFileDir) {
                    makeFileDir = extension.makeFileDir
                }

                if (!makeOutputDir) {
                    makeOutputDir = extension.makeOutputDir
                }
            }
        }
    }
}
