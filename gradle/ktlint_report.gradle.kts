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
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

tasks.register("ktlintReport") {

    description = "project ktlint report"
    group = "ktlint report"

    allprojects.forEach { dependsOn("${it.path}:ktlintCheck") }

    doLast {
        val checkstyleFileSuffix = "Check.xml"
        val inputDirs =
            allprojects
                .asSequence()
                .map {
                    it.layout.buildDirectory
                        .file("ktlintReports")
                        .get()
                        .asFile
                }.filter {
                    it.exists()
                }.filter { dir ->
                    dir.listFiles()?.any { file ->
                        file.name.endsWith(checkstyleFileSuffix)
                    } ?: false
                }.toList()

        val checkstyleFiles = mutableListOf<File>()
        inputDirs.forEach { dir ->
            dir.listFiles()?.forEach { file ->
                if (file.isFile && file.name.endsWith(checkstyleFileSuffix)) {
                    checkstyleFiles.add(file)
                }
            }
        }
        logger.quiet("${checkstyleFiles.size} checkstyle files need to merge")

        val outputFile =
            rootProject
                .layout
                .buildDirectory
                .file("ktlintReport/ktlintReport.xml")
                .get()
                .asFile
        outputFile.parentFile?.mkdirs()

        // create root node
        val mergedDoc =
            DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument()

        val rootElement = mergedDoc.createElement("checkstyle")
        rootElement.setAttribute("version", "8.0")
        mergedDoc.appendChild(rootElement)

        fun findOrCreateFileNode(
            doc: Document,
            parent: Element,
            filePath: String,
        ): Element {
            // find node
            val fileNodes = parent.getElementsByTagName("file")
            for (i in 0 until fileNodes.length) {
                val fileNode = fileNodes.item(i) as Element
                if (fileNode.getAttribute("name") == filePath) {
                    return fileNode
                }
            }

            // create node
            val newFileNode = doc.createElement("file")
            newFileNode.setAttribute("name", filePath)
            parent.appendChild(newFileNode)
            return newFileNode
        }

        var hasError = false
        checkstyleFiles.forEach { file ->
            try {
                val doc =
                    DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(file)

                val fileNodes = doc.getElementsByTagName("file")
                for (i in 0 until fileNodes.length) {
                    val fileNode = fileNodes.item(i) as Element
                    val filePath = fileNode.getAttribute("name")
                    val mergedFileNode = findOrCreateFileNode(mergedDoc, rootElement, filePath)

                    logger.error("\n⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️")
                    logger.error("FilePath: $filePath")
                    val errorNodes = fileNode.getElementsByTagName("error")
                    for (j in 0 until errorNodes.length) {
                        val errorNode = errorNodes.item(j)
                        val importedError = mergedDoc.importNode(errorNode, true)
                        mergedFileNode.appendChild(importedError)
                        hasError = true

                        val nodeMap = errorNode.attributes
                        logger.error(
                            """
                            
                            Error $j: ${nodeMap.getNamedItem("line")} ${nodeMap.getNamedItem("column")}
                            ${nodeMap.getNamedItem("message")}
                            ${nodeMap.getNamedItem("severity")} ${nodeMap.getNamedItem("source")}
                            """.trimIndent(),
                        )
                    }
                    logger.error("⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️⬆️")
                }
            } catch (e: Exception) {
                logger.error("Merge ${file.name} error: ${e.message}")
            }
        }

        // 写入合并后的XML文件
        val transformer =
            TransformerFactory
                .newInstance()
                .newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")

        val result = StreamResult(outputFile)
        val source = DOMSource(mergedDoc)
        transformer.transform(source, result)

        if (hasError) {
            throw GradleException("ktlint check has error, see ${outputFile.path}")
        } else {
            logger.quiet("No error")
        }
    }
}
