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

package com.reach.kmp.feature.learn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object LearnScreenSample

fun NavGraphBuilder.learnRoute(navController: NavController) {
    composable<LearnScreenSample> {
        LearnRoute(navController)
    }
}

@Composable
private fun LearnRoute(
    navController: NavController,
    viewModel: LearnVM = koinViewModel(),
) {
    val text by viewModel.text.collectAsStateWithLifecycle()

    LearnScreen(
        onBackClick = { navController.navigateUp() },
        text = text,
    )
}

@Composable
private fun LearnScreen(
    onBackClick: () -> Unit,
    text: String,
) {
    TitleBarWithBack(onBackClick = onBackClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleBarWithBack(
    onBackClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                        contentDescription = "",
                        modifier =
                            Modifier
                                .clickable { onBackClick() }
                                .padding(12.dp),
                    )
                },
            )

            content()
        }
    }
}

@Preview
@Composable
private fun LearnScreenPreview() {
    LearnScreen(
        onBackClick = {},
        text = "Hello Compose",
    )
}
