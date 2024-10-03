package com.example.cookbook.presentation.local

import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter

data class RecipeData(
    val title: String,
    val content: String,
    val image: Painter
)
