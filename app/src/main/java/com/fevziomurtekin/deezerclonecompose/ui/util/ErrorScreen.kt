package com.fevziomurtekin.deezerclonecompose.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorScreen(
    retryClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "We've unexpected error. Please try again.")
            Button(
                onClick = retryClick,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Blue
                )
            ) {
                Text(text = "Try Again", color= Color.White)
            }
        }
    }


}