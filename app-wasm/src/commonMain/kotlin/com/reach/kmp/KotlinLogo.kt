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

package com.reach.kmp

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KotlinLogo: ImageVector
    get() {
        if (_kotlinLogo != null) {
            return _kotlinLogo!!
        }
        _kotlinLogo =
            ImageVector
                .Builder(
                    name = "Kotlin Full Color Logo Mark RGB",
                    defaultWidth = 500.dp,
                    defaultHeight = 500.dp,
                    viewportWidth = 500f,
                    viewportHeight = 500f,
                ).apply {
                    path(
                        fill =
                            Brush.linearGradient(
                                colorStops =
                                    arrayOf(
                                        0f to Color(0xFFE44857),
                                        0.47f to Color(0xFFC711E1),
                                        1f to Color(0xFF7F52FF),
                                    ),
                                start = Offset(500f, 0f),
                                end = Offset(0f, 500f),
                            ),
                    ) {
                        moveTo(500f, 500f)
                        lineToRelative(-500f, 0f)
                        lineToRelative(0f, -500f)
                        lineToRelative(500f, 0f)
                        lineToRelative(-250f, 250f)
                        close()
                    }
                }.build()

        return _kotlinLogo!!
    }

@Suppress("ObjectPropertyName")
private var _kotlinLogo: ImageVector? = null
