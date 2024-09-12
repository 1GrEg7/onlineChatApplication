package com.example.testchat.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun messageForm(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
    {
        Row( modifier = Modifier.fillMaxWidth(0.35f), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Гриша",  fontSize = 7.sp)
            Text(text = "15:09", fontSize = 5.sp)
        }
        Text(text = "Что-то пишем, просто сообщение в будущем",fontSize = 10.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun preview(){
    messageForm()
}