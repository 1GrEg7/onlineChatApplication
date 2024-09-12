package com.example.testchat.screens

import android.widget.HorizontalScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun previewChatTest(){

    val listOfMessage =   remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF42A5F5)), verticalArrangement = Arrangement.SpaceEvenly){
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)){
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd)) {
                Text(text = "Sign Out", fontSize = 20.sp , color = Color.Black, textDecoration = TextDecoration.Underline)
            }
            Text("Chat", fontSize = 30.sp, modifier = Modifier.align(Alignment.Center))

            TextButton(onClick = { /*TODO*/ },modifier = Modifier. align(Alignment.TopStart) ) {
                Text(text = "Задать имя", fontSize = 20.sp , color = Color.Black, textDecoration = TextDecoration.Underline)
            }
        }

        @Composable
        fun messageForm(){
            Box( modifier = Modifier
                .padding(8.dp, 8.dp, 8.dp, 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(0.35f)
                .wrapContentHeight()
                .wrapContentWidth()
                .background(color = Color(0xFF0066FF))
                .align(Alignment.Start) ){

                Column() {
                    Box( modifier = Modifier
                        .fillMaxWidth()
                    ) {
                        Text(modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 5.dp, top = 3.dp, bottom = 3.dp) ,text = "Гриша", style = TextStyle(Color(0xFFF0FFF0)), fontSize = 7.sp)
                        Text(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 5.dp, top = 3.dp),text = "15:09", fontSize = 5.sp, style = TextStyle(Color(0xFFF0FFF0)))
                    }
                    Text(modifier = Modifier.padding(start = 5.dp, bottom = 5.dp), style = TextStyle(Color(0xFFD3D3D3)),text = "Что-то пишем, просто сообщение в будущем jffffffffffffffffffffffff dkkvkffdf  kdjfhdkjhfdkjfhjdfh dkjfhdjkhkjdfhk dkjfhdkj",fontSize = 10.sp)
                }

            }

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(7f)
            .background(Color(0xFF0FBFFF))
            .verticalScroll(rememberScrollState(), enabled = true)
        ) {
            messageForm()

//            for(i in listOfMessage){
//                Text(text = i, modifier = textModifier )
//            }

        }

        Row(modifier = Modifier
            .fillMaxSize()
            .weight(1f), horizontalArrangement = Arrangement.SpaceEvenly){
            var message =  remember{mutableStateOf("")}
            Box(modifier = Modifier
                .weight(4f)
                .padding(8.dp)){
                TextField(value = message.value, onValueChange ={newText-> message.value = newText}, maxLines = 1, modifier = Modifier.fillMaxSize())
            }
            Box(modifier = Modifier.weight(1f)){
                IconButton(onClick = { listOfMessage.add(message.value) },modifier = Modifier
                    .fillMaxSize()
                    .size(40.dp)
                ) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Massage", modifier = Modifier
                        .size(40.dp)
                    )

                }
            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun previewChatTestView(){
    previewChatTest()
}