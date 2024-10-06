package com.example.cookbook.presentation.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.cookbook.R
import com.example.cookbook.presentation.local.RecipeData

@Composable
fun RecipeInfoScreen(
    recipeData: RecipeData,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = recipeData.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = recipeData.image,
            contentDescription = "Recipe image",
        )
        Text(
            text = recipeData.content,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            lineHeight = 20.sp
        )
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Back")
        }
    }
}