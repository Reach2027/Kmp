package com.reach.kmp.ui.core.common.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable

object AppAniSpec {

    const val STIFFNESS_WIDGET_ENTER = 800f
    const val STIFFNESS_WIDGET_EXIT = 1200f
    const val STIFFNESS_WIDGET_EXIT_LOW = 800f

    const val STIFFNESS_GROUP_ENTER = 400f
    const val STIFFNESS_GROUP_EXIT = 600f
    const val STIFFNESS_GROUP_EXIT_LOW = 400f

    const val DAMPING_RATIO = 0.6f
}

@Stable
fun <T> widgetEnter(
    dampingRatio: Float = Spring.DampingRatioNoBouncy,
    stiffness: Float = AppAniSpec.STIFFNESS_WIDGET_ENTER,
    visibilityThreshold: T? = null,
): SpringSpec<T> = spring(
    dampingRatio = dampingRatio,
    stiffness = stiffness,
    visibilityThreshold = visibilityThreshold,
)

@Stable
fun <T> widgetExit(
    dampingRatio: Float = Spring.DampingRatioNoBouncy,
    stiffness: Float = AppAniSpec.STIFFNESS_WIDGET_EXIT,
    visibilityThreshold: T? = null,
): SpringSpec<T> = spring(
    dampingRatio = dampingRatio,
    stiffness = stiffness,
    visibilityThreshold = visibilityThreshold,
)

@Stable
fun <T> groupEnter(
    dampingRatio: Float = Spring.DampingRatioNoBouncy,
    stiffness: Float = AppAniSpec.STIFFNESS_GROUP_ENTER,
    visibilityThreshold: T? = null,
): SpringSpec<T> = spring(
    dampingRatio = dampingRatio,
    stiffness = stiffness,
    visibilityThreshold = visibilityThreshold,
)

@Stable
fun <T> groupExit(
    dampingRatio: Float = Spring.DampingRatioNoBouncy,
    stiffness: Float = AppAniSpec.STIFFNESS_GROUP_EXIT,
    visibilityThreshold: T? = null,
): SpringSpec<T> = spring(
    dampingRatio = dampingRatio,
    stiffness = stiffness,
    visibilityThreshold = visibilityThreshold,
)
