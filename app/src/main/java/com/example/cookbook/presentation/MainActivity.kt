/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.cookbook.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.cookbook.R
import com.example.cookbook.presentation.local.RecipeData
import com.example.cookbook.presentation.theme.CookBookTheme
import com.example.cookbook.presentation.view.composable.RecipeInfoScreen
import com.example.cookbook.presentation.view.composable.RecipeScreen
import com.example.cookbook.presentation.view.composable.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            CookBookTheme {
                var currentScreen by remember { mutableStateOf("start") }
                var selectedRecipe by remember { mutableStateOf<RecipeData?>(null) }

                when (currentScreen) {
                    "start" -> {
                        StartScreen(
                            switchScreen = {
                                currentScreen = "recipe_list"
                            }
                        )
                    }
                    "recipe_list" -> {
                        if (selectedRecipe == null) {
                            RecipeScreen(
                                context = this ,
                                onRecipeClick = { recipe ->
                                    selectedRecipe = recipe
                                }
                            )
                        } else {
                            currentScreen = "recipe_info"
                        }
                    }
                    "recipe_info" -> {
                        RecipeInfoScreen(
                            recipeData = selectedRecipe!!,
                            onBackClick = {
                                selectedRecipe = null
                                currentScreen = "recipe_list"
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(device = WearDevices.SQUARE, showSystemUi = true)
@Composable
fun DefaultPreview() {

}