package com.example.testchat.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testchat.presentation.sign_in.SignInState





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun previewLoginTest(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Log in", fontSize = 25.sp, modifier = Modifier.padding(bottom = 110.dp))
            Text(text = "Name:" , fontSize = 25.sp, modifier = Modifier.padding( end = 210.dp, bottom = 10.dp))
            TextField(value = "Registration name",
                onValueChange = {},
            )
            Text("Password:", fontSize = 25.sp, modifier = Modifier.padding( end = 167.dp, bottom = 10.dp, top = 10.dp))
            TextField(value = "New Password", onValueChange ={} )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                OutlinedButton(onClick = { }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                    Text(text = "Sign in", fontSize = 25.sp, color = Color.Black,)
                }
                OutlinedButton(onClick = { }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                    Text(text = "         Back \n to registration", fontSize = 18.sp, color = Color.Black,)
                }
            }
        }

    }


}
@Preview(showBackground = true)
@Composable
fun previewLoginTestView(){

    previewLoginTest()
}