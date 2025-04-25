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

package com.reach.kmp.shared

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CmpLogo: ImageVector
    get() {
        if (_cmpLogo != null) {
            return _cmpLogo!!
        }
        _cmpLogo =
            ImageVector
                .Builder(
                    name = "CmpLogo",
                    defaultWidth = 600.dp,
                    defaultHeight = 600.dp,
                    viewportWidth = 600f,
                    viewportHeight = 600f,
                ).apply {
                    path(fill = SolidColor(Color(0xFF041619))) {
                        moveTo(301.21f, 418.53f)
                        curveTo(300.97f, 418.54f, 300.73f, 418.56f, 300.49f, 418.56f)
                        curveTo(297.09f, 418.59f, 293.74f, 417.72f, 290.79f, 416.05f)
                        lineTo(222.6f, 377.54f)
                        curveTo(220.63f, 376.43f, 219f, 374.82f, 217.85f, 372.88f)
                        curveTo(216.7f, 370.94f, 216.09f, 368.73f, 216.07f, 366.47f)
                        lineTo(216.07f, 288.16f)
                        curveTo(216.06f, 287.32f, 216.09f, 286.49f, 216.17f, 285.67f)
                        curveTo(216.38f, 283.54f, 216.91f, 281.5f, 217.71f, 279.6f)
                        lineTo(199.29f, 268.27f)
                        lineTo(177.74f, 256.19f)
                        curveTo(175.72f, 260.43f, 174.73f, 265.23f, 174.78f, 270.22f)
                        lineTo(174.79f, 387.05f)
                        curveTo(174.85f, 393.89f, 178.57f, 400.2f, 184.53f, 403.56f)
                        lineTo(286.26f, 461.02f)
                        curveTo(290.67f, 463.51f, 295.66f, 464.8f, 300.73f, 464.76f)
                        curveTo(300.91f, 464.76f, 301.09f, 464.74f, 301.27f, 464.74f)
                        curveTo(301.24f, 449.84f, 301.22f, 439.23f, 301.22f, 439.23f)
                        lineTo(301.21f, 418.53f)
                        close()
                    }
                    path(fill = SolidColor(Color(0xFF37BF6E))) {
                        moveTo(409.45f, 242.91f)
                        lineTo(312.64f, 188.23f)
                        curveTo(303.64f, 183.15f, 292.58f, 183.26f, 283.68f, 188.51f)
                        lineTo(187.92f, 245f)
                        curveTo(183.31f, 247.73f, 179.93f, 251.62f, 177.75f, 256.17f)
                        lineTo(177.74f, 256.19f)
                        lineTo(199.29f, 268.27f)
                        lineTo(217.71f, 279.6f)
                        curveTo(217.83f, 279.32f, 217.92f, 279.02f, 218.05f, 278.74f)
                        curveTo(218.24f, 278.36f, 218.43f, 277.98f, 218.64f, 277.62f)
                        curveTo(219.06f, 276.88f, 219.52f, 276.18f, 220.04f, 275.51f)
                        curveTo(221.37f, 273.8f, 223.01f, 272.35f, 224.87f, 271.25f)
                        lineTo(289.06f, 233.39f)
                        curveTo(290.42f, 232.59f, 291.87f, 231.96f, 293.39f, 231.51f)
                        curveTo(295.53f, 230.87f, 297.77f, 230.6f, 300f, 230.72f)
                        curveTo(302.98f, 230.88f, 305.88f, 231.73f, 308.47f, 233.2f)
                        lineTo(373.37f, 269.85f)
                        curveTo(375.54f, 271.08f, 377.49f, 272.68f, 379.13f, 274.57f)
                        curveTo(379.68f, 275.19f, 380.18f, 275.85f, 380.65f, 276.53f)
                        curveTo(380.86f, 276.84f, 381.05f, 277.15f, 381.24f, 277.47f)
                        lineTo(397.79f, 266.39f)
                        lineTo(420.34f, 252.93f)
                        lineTo(420.31f, 252.88f)
                        curveTo(417.55f, 248.8f, 413.77f, 245.35f, 409.45f, 242.91f)
                        close()
                    }
                    path(fill = SolidColor(Color(0xFF3870B2))) {
                        moveTo(381.24f, 277.47f)
                        curveTo(381.51f, 277.92f, 381.77f, 278.38f, 382.01f, 278.84f)
                        curveTo(382.21f, 279.24f, 382.39f, 279.65f, 382.57f, 280.06f)
                        curveTo(382.91f, 280.88f, 383.19f, 281.73f, 383.41f, 282.59f)
                        curveTo(383.74f, 283.88f, 383.92f, 285.21f, 383.93f, 286.57f)
                        lineTo(383.93f, 361.1f)
                        curveTo(383.96f, 363.95f, 383.35f, 366.77f, 382.16f, 369.36f)
                        curveTo(381.93f, 369.86f, 381.69f, 370.35f, 381.42f, 370.83f)
                        curveTo(379.75f, 373.79f, 377.32f, 376.27f, 374.39f, 378f)
                        lineTo(310.2f, 415.87f)
                        curveTo(307.47f, 417.48f, 304.38f, 418.39f, 301.21f, 418.53f)
                        lineTo(301.22f, 439.23f)
                        curveTo(301.22f, 439.23f, 301.24f, 449.84f, 301.27f, 464.74f)
                        curveTo(306.1f, 464.61f, 310.91f, 463.3f, 315.21f, 460.75f)
                        lineTo(410.98f, 404.25f)
                        curveTo(419.88f, 399f, 425.31f, 389.37f, 425.22f, 379.03f)
                        lineTo(425.22f, 267.85f)
                        curveTo(425.17f, 262.48f, 423.34f, 257.34f, 420.34f, 252.93f)
                        lineTo(397.79f, 266.39f)
                        lineTo(381.24f, 277.47f)
                        close()
                    }
                    path(
                        stroke = SolidColor(Color(0xFF083042)),
                        strokeLineWidth = 10f,
                    ) {
                        moveTo(177.75f, 256.17f)
                        curveTo(179.93f, 251.62f, 183.31f, 247.73f, 187.92f, 245f)
                        lineTo(283.68f, 188.51f)
                        curveTo(292.58f, 183.26f, 303.64f, 183.15f, 312.64f, 188.23f)
                        lineTo(409.45f, 242.91f)
                        curveTo(413.77f, 245.35f, 417.55f, 248.8f, 420.31f, 252.88f)
                        lineTo(420.34f, 252.93f)
                        lineTo(498.59f, 206.19f)
                        curveTo(494.03f, 199.46f, 487.79f, 193.78f, 480.67f, 189.75f)
                        lineTo(320.86f, 99.49f)
                        curveTo(306.01f, 91.1f, 287.75f, 91.27f, 273.07f, 99.95f)
                        lineTo(114.99f, 193.2f)
                        curveTo(107.39f, 197.69f, 101.81f, 204.11f, 98.21f, 211.63f)
                        lineTo(177.74f, 256.19f)
                        lineTo(177.75f, 256.17f)
                        close()
                        moveTo(301.27f, 464.74f)
                        curveTo(301.09f, 464.74f, 300.91f, 464.76f, 300.73f, 464.76f)
                        curveTo(295.66f, 464.8f, 290.67f, 463.51f, 286.26f, 461.02f)
                        lineTo(184.53f, 403.56f)
                        curveTo(178.57f, 400.2f, 174.85f, 393.89f, 174.79f, 387.05f)
                        lineTo(174.78f, 270.22f)
                        curveTo(174.73f, 265.23f, 175.72f, 260.43f, 177.74f, 256.19f)
                        lineTo(98.21f, 211.63f)
                        curveTo(94.86f, 218.63f, 93.23f, 226.58f, 93.31f, 234.82f)
                        lineTo(93.31f, 427.67f)
                        curveTo(93.42f, 438.97f, 99.54f, 449.37f, 109.4f, 454.92f)
                        lineTo(277.31f, 549.77f)
                        curveTo(284.6f, 553.88f, 292.84f, 556.01f, 301.2f, 555.94f)
                        lineTo(301.2f, 555.8f)
                        curveTo(301.39f, 543.78f, 301.33f, 495.26f, 301.27f, 464.74f)
                        close()
                    }
                    path(
                        stroke = SolidColor(Color(0xFF083042)),
                        strokeLineWidth = 10f,
                    ) {
                        moveTo(498.59f, 206.19f)
                        lineTo(420.34f, 252.93f)
                        curveTo(423.34f, 257.34f, 425.17f, 262.48f, 425.22f, 267.85f)
                        lineTo(425.22f, 379.03f)
                        curveTo(425.31f, 389.37f, 419.88f, 399f, 410.98f, 404.25f)
                        lineTo(315.21f, 460.75f)
                        curveTo(310.91f, 463.3f, 306.1f, 464.61f, 301.27f, 464.74f)
                        curveTo(301.33f, 495.26f, 301.39f, 543.78f, 301.2f, 555.8f)
                        lineTo(301.2f, 555.94f)
                        curveTo(309.48f, 555.87f, 317.74f, 553.68f, 325.11f, 549.32f)
                        lineTo(483.18f, 456.06f)
                        curveTo(497.87f, 447.39f, 506.85f, 431.49f, 506.69f, 414.43f)
                        lineTo(506.69f, 230.91f)
                        curveTo(506.6f, 222.02f, 503.57f, 213.5f, 498.59f, 206.19f)
                        close()
                    }
                    path(
                        stroke = SolidColor(Color(0xFF083042)),
                        strokeLineWidth = 10f,
                    ) {
                        moveTo(301.2f, 555.94f)
                        curveTo(292.84f, 556.01f, 284.6f, 553.88f, 277.31f, 549.76f)
                        lineTo(109.4f, 454.92f)
                        curveTo(99.54f, 449.37f, 93.42f, 438.97f, 93.31f, 427.67f)
                        lineTo(93.31f, 234.82f)
                        curveTo(93.23f, 226.58f, 94.86f, 218.63f, 98.21f, 211.63f)
                        curveTo(101.81f, 204.11f, 107.39f, 197.69f, 114.99f, 193.2f)
                        lineTo(273.07f, 99.95f)
                        curveTo(287.75f, 91.27f, 306.01f, 91.1f, 320.86f, 99.49f)
                        lineTo(480.67f, 189.75f)
                        curveTo(487.79f, 193.78f, 494.03f, 199.46f, 498.59f, 206.19f)
                        curveTo(503.57f, 213.5f, 506.6f, 222.02f, 506.69f, 230.91f)
                        lineTo(506.69f, 414.43f)
                        curveTo(506.85f, 431.49f, 497.87f, 447.39f, 483.18f, 456.06f)
                        lineTo(325.11f, 549.32f)
                        curveTo(317.74f, 553.68f, 309.48f, 555.87f, 301.2f, 555.94f)
                        close()
                    }
                }.build()

        return _cmpLogo!!
    }

@Suppress("ObjectPropertyName")
private var _cmpLogo: ImageVector? = null
