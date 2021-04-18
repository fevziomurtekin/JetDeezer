package com.fevziomurtekin.deezerclonecompose.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fevziomurtekin.deezerclonecompose.R

@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(color = Color.White),
        contentAlignment = Alignment.Center,

    ) {
        Image(painterResource(id = R.drawable.ic_deezer), contentDescription = null)
    }
}