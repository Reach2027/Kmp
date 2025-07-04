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

package com.reach.kmp.ui.base.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Stable
@Composable
fun Int.toDp(): Dp = with(LocalDensity.current) { toDp() }

@Stable
@Composable
fun Float.toDp(): Dp = with(LocalDensity.current) { toDp() }

@Stable
@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { toPx() }

@Stable
@Composable
fun Dp.toSp(): TextUnit = with(LocalDensity.current) { toSp() }
