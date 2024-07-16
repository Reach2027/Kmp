package com.reach.kmp.ui.base.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Stable
@Composable
fun Int.toDp(): Dp = with(LocalDensity.current) { toDp() }

@Stable
@Composable
fun Float.toDp(): Dp = with(LocalDensity.current) { toDp() }

@Stable
@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { toPx() }
