package com.example.cookbook.presentation.view.composable

import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.cookbook.presentation.local.RecipeData
import readRecipesFromAssets


@Composable
fun RecipeScreen(
    context: Context,
    onRecipeClick: (RecipeData) -> Unit
) {
    val allRecipe = readRecipesFromAssets(context)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(allRecipe) { recipe ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp.dp / 4))
                    .padding(4.dp)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(15))
                    .clickable { onRecipeClick(recipe) }
            ) {
                Image(
                    painter = recipe.image,
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 3.dp)
                        .clip(RoundedCornerShape(topStartPercent = 15, bottomStartPercent = 15)),
                    contentScale = ContentScale.FillHeight
                )

                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}