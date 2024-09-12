package com.example.testchat.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.testchat.BackEnd.SignUpInfo






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun previewRegistrationTest(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Registration", fontSize = 25.sp, modifier = Modifier.padding(bottom = 110.dp))
            Text(text = "Name:" , fontSize = 25.sp, modifier = Modifier.padding( end = 210.dp, bottom = 10.dp))
            TextField(value = "Registration name",
                onValueChange = {},
            )
            Text("Password:", fontSize = 25.sp, modifier = Modifier.padding( end = 167.dp, bottom = 10.dp, top = 10.dp))
            TextField(value = "New Password", onValueChange ={} )
            Text("Confirm password:", fontSize = 25.sp, modifier = Modifier.padding( end = 75.dp, bottom = 10.dp, top = 10.dp))
            TextField(value = "Password", onValueChange ={} )

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                OutlinedButton(onClick = {  }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                    Text(text = "sign up", fontSize = 25.sp, color = Color.Black,)
                }
                OutlinedButton(onClick = {  }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                    Text(text = "Go to Log in screen", softWrap = true, fontSize = 15.sp, color = Color.Black,)
                }
            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun previewRegistrationTestView(){

    previewRegistrationTest()
}