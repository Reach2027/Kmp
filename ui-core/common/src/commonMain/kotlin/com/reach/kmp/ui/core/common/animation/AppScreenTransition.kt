package com.reach.kmp.ui.core.common.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.IntOffset
import com.reach.kmp.ui.core.common.animation.groupEnter
import com.reach.kmp.ui.core.common.animation.groupExit

@Stable
fun topDestEnterTransition(): EnterTransition = fadeIn(animationSpec = groupEnter())

@Stable
fun topDestExitTransition(): ExitTransition = fadeOut(animationSpec = groupExit())

@Stable
fun enterScreenTransition(): EnterTransition = slideInHorizontally(
    animationSpec = groupEnter(visibilityThreshold = IntOffset.VisibilityThreshold),
    initialOffsetX = { it },
)

@Stable
fun exitScreenTransition(): ExitTransition = slideOutHorizontally(
    animationSpec = groupExit(visibilityThreshold = IntOffset.VisibilityThreshold),
    targetOffsetX = { -it / 4 },
) + fadeOut(animationSpec = groupExit())

@Stable
fun popEnterScreenTransition(): EnterTransition = slideInHorizontally(
    animationSpec = groupEnter(visibilityThreshold = IntOffset.VisibilityThreshold),
    initialOffsetX = { -it / 4 },
) + fadeIn(animationSpec = groupEnter())

@Stable
fun popExitScreenTransition(): ExitTransition = slideOutHorizontally(
    animationSpec = groupExit(visibilityThreshold = IntOffset.VisibilityThreshold),
    targetOffsetX = { it },
)
