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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.reach.kmp.feature.bingwallpaper.RouteBingWallpaper
import com.reach.kmp.feature.compose.RouteScreenSample
import kotlinx.serialization.Serializable

@Serializable
object RouteStart

fun NavGraphBuilder.startRoute(navController: NavController) {
    composable<RouteStart> {
        StartRoute(navController)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StartRoute(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 32.dp,
                alignment = Alignment.CenterHorizontally,
            ),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Button(
                modifier = Modifier.padding(horizontal = 32.dp),
                onClick = { navController.navigate(RouteScreenSample) },
            ) {
                Text(text = "Screen Sample")
            }

            Button(
                modifier = Modifier.padding(horizontal = 32.dp),
                onClick = { navController.navigate(RouteBingWallpaper) },
            ) {
                Text(text = "Bing Wallpaper")
            }
        }
    }
}
