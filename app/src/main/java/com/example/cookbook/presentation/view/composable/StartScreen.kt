package com.example.cookbook.presentation.view.composable

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.cookbook.R

@Composable
fun StartScreen(switchScreen: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.cookie),
            contentDescription = "Cookie image",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Text(
            text = "CookBook",
            fontSize = 24.sp
        )
        Button(
            onClick = switchScreen,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow, shape = RoundedCornerShape(10))
        ) {
            Text ("Next")
        }
    }
}