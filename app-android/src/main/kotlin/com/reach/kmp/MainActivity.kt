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

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.viewinterop.AndroidView
import com.reach.kmp.shared.App

private val LightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                darkCallback = { isDark ->
                    enableEdgeToEdge(
                        statusBarStyle =
                            SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) {
                                isDark
                            },
                        navigationBarStyle =
                            SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) {
                                isDark
                            },
                    )
                },
                nativeUI = {
                    AndroidView(
                        factory = { cxt ->
                            TextView(cxt).apply {
                                text = "I am Android TextView"
                                setTextColor(Color.CYAN)
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                            }
                        },
                    )
                },
            )
        }
    }
}
