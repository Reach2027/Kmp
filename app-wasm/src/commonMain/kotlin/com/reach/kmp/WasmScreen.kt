/*
 * Copyright 2024 Reach Project
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

package com.reach.kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun WasmSample() {
    val colorScheme =
        if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }

    MaterialTheme(colorScheme = colorScheme) {
        var showContent by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(
                visible = showContent,
                enter =
                    expandVertically(
                        animationSpec =
                            spring(
                                stiffness = 5f,
                                visibilityThreshold = IntSize.VisibilityThreshold,
                            ),
                    ),
                exit =
                    shrinkVertically(
                        animationSpec =
                            spring(
                                stiffness = 5f,
                                visibilityThreshold = IntSize.VisibilityThreshold,
                            ),
                    ),
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Hello Compose Wasm",
                        modifier = Modifier.padding(top = 64.dp),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.W600,
                    )

                    Row(
                        modifier = Modifier.padding(top = 32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            imageVector = KotlinLogo,
                            contentDescription = "Kotlin logo",
                            modifier =
                                Modifier
                                    .padding(top = 24.dp, start = 42.dp)
                                    .width(192.dp)
                                    .height(192.dp),
                        )
                        Spacer(modifier = Modifier.width(128.dp))
                        Image(
                            imageVector = CmpLogo,
                            contentDescription = "Compose multiplatform logo",
                            modifier =
                                Modifier
                                    .width(258.dp)
                                    .height(258.dp),
                        )
                    }
                }
            }
        }
    }
}
