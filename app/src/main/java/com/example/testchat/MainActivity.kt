package com.example.testchat

import ChatMessage
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Identity
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import androidx.navigation.compose.rememberNavController
import com.example.testchat.ui.theme.TestChatTheme
import com.google.firebase.messaging.remoteMessage

//import com.example.testchat.screens.Registration

import androidx.navigation.compose.*
//import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testchat.presentation.sign_in.GoogleAuthUiClient
import com.example.testchat.presentation.sign_in.SignInViewModel

import com.google.android.gms.auth.api.identity.Identity.getSignInClient

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.example.testchat.BackEnd.SignUpInfo
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale




import kotlin.concurrent.thread


fun formatTime(time: Long): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(Date(time))
}
fun findSpaces(str:String):Boolean{
    if (str.contains(" ") || str.contains(Regex("\\n"))){
        return true
    }
    return false
}
class MainActivity : ComponentActivity() {



    private lateinit var auth: FirebaseAuth
    val database = Firebase.database("https://testchat-b9f77-default-rtdb.europe-west1.firebasedatabase.app")
    val myRef = database.getReference("messages")
    private lateinit var chatViewModel: ViewModelChat




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            auth = Firebase.auth




        setContent {
            TestChatTheme {
//
                ComposeNavigation()
            }
        }
    }




    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null){
            // отправоляем на регистрацию
            // reload()
        }
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }





    fun signIn(email: String, password: String, navController: NavController){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    navController.navigate("chat_screen")
                    //updateUI(user)
                }else{
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext,"Неверная почта или пароль",Toast.LENGTH_LONG).show()
                }
            }
    }
    fun createAccount(email:String, password:String, navController:NavController){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    navController.navigate("chat_screen")

                    // updateUI(user)
                }else{
                    Log.d("111", "accaunt does not created")
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_LONG).show()
                    Toast.makeText( baseContext, "Почты не существует или она уже зарегестрирована", Toast.LENGTH_LONG).show()
                }
            }
    }


    @Composable
    fun ComposeNavigation(){

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "registration_screen") {
            composable("registration_screen") {
                Registration(navController = navController)
            }
            composable("login_screen") {
                Login(navController = navController)
            }

            composable("chat_screen") {
                chatViewModel =  viewModel()
                Chat(navController = navController, chatViewModel)
            }
        }
    }




    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Registration(navController: NavController){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Registration", fontSize = 25.sp, modifier = Modifier.padding(bottom = 110.dp))
                var email = remember { mutableStateOf("") }
                Text(text = "Email:" , fontSize = 25.sp, modifier = Modifier.padding( end = 210.dp, bottom = 10.dp))
                TextField(value = email.value,
                    onValueChange = {newText -> email.value = newText},
                    maxLines = 1
                )
                var password = remember { mutableStateOf("") }
                Text("Password:", fontSize = 25.sp, modifier = Modifier.padding( end = 167.dp, bottom = 10.dp, top = 10.dp))
                TextField(value = password.value, onValueChange ={newText -> password.value= newText},maxLines = 1 )
                var confirmPassword = remember { mutableStateOf("") }
                Text("Confirm password:", fontSize = 25.sp, modifier = Modifier.padding( end = 75.dp, bottom = 10.dp, top = 10.dp))
                TextField(value = confirmPassword.value, maxLines = 1, onValueChange ={newText -> confirmPassword.value = newText } )

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                    OutlinedButton(onClick = {
                    if (password.value==confirmPassword.value){//
                        if (password.value.trim()!=""){
                            if (password.value.length<6){
                                Toast.makeText(baseContext,"Пароль должен содержать не меньше 6 символов",Toast.LENGTH_LONG).show()
                            }else{
                                createAccount(email.value,password.value, navController)
                            }
                        } else{
                            Toast.makeText(baseContext, "Пустые поля", Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(baseContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    }
                         },
                        modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                        Text(text = "sign up", fontSize = 25.sp, color = Color.Black,)
                    }
                    OutlinedButton(onClick = {
                            navController.navigate("login_screen")
                                             }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                        Text(text = "Go to Log in screen", softWrap = true, fontSize = 15.sp, color = Color.Black,)
                    }
                }
            }

        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Login(navController: NavController)
    {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Log in", fontSize = 25.sp, modifier = Modifier.padding(bottom = 110.dp))
                Text(text = "Email:" , fontSize = 25.sp, modifier = Modifier.padding( end = 210.dp, bottom = 10.dp))
                var email = remember { mutableStateOf("") }
                TextField(value = email.value,
                    onValueChange = {newText -> email.value = newText},
                    maxLines = 1
                )
                Text("Password:", fontSize = 25.sp, modifier = Modifier.padding( end = 167.dp, bottom = 10.dp, top = 10.dp))
                var password = remember { mutableStateOf("") }
                TextField(value = password.value, onValueChange ={newText -> password.value = newText},maxLines = 1 )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedButton(onClick = {
                        try {
                            navController.navigate("registration_screen")
                        }   catch (e:Exception){

                        }

                                             },
                        modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                        Text(text = "         Back \n to registration", fontSize = 18.sp, color = Color.Black,)
                    }
                    OutlinedButton(onClick = {
                        try {
                            signIn(email.value,password.value,navController)
                        }catch (e:Exception){

                        }

                                             }, modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(5.dp) ) {
                        Text(text = "Sign in", fontSize = 25.sp, color = Color.Black,)
                    }

                }
            }

        }
    }





    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Chat(navController: NavController,viewModel: ViewModelChat ){

        var openDialog = remember { mutableStateOf(false) } // Переменная для управления состоянием диалога
        var name = remember { mutableStateOf("") } // Состояние для хранения имени

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun CreateDialogChangeName() {


            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false }, // Устанавливаем false при закрытии
                    title = {
                        Column {
                            Text(text = "Отображаемое имя")
                            TextField(value = name.value, onValueChange = { newText ->  name.value = newText })
                        }
                    },
                    confirmButton = {
                        OutlinedButton(onClick = {
                            if (name.value.length<16 && !findSpaces(name.value)){
                                openDialog.value = false
                                viewModel.userName = name.value
                            }else{
                                Toast.makeText(this,"Должно быть не больше 15 символов без пробелов",Toast.LENGTH_LONG).show()
                            }

                        })
                        {
                            Text(text = "Сохранить")
                        }
                    },
                    dismissButton = {
                        Row (
                            horizontalArrangement = Arrangement.Start
                        ){
                            OutlinedButton(onClick = { openDialog.value = false }, modifier = Modifier.padding(0.dp,0.dp,40.dp,0.dp)) {
                                Text(text = "Отмена")
                            }
                        }

                    }
                )
            }
        }


        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF42A5F5)), verticalArrangement = Arrangement.SpaceEvenly){
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f)){
                TextButton(onClick = {
                    auth.signOut()
                    navController.navigate("registration_screen")},
                    modifier = Modifier.align(Alignment.TopEnd)) {
                    Text(text = "Sign Out", fontSize = 20.sp , color = Color.Black, textDecoration = TextDecoration.Underline)
                }

                Text("Chat", fontSize = 30.sp, modifier = Modifier.align(Alignment.Center))

                TextButton(onClick = { openDialog.value = true   },modifier = Modifier. align(Alignment.TopStart) ) {
                    Text(text = "Задать имя", fontSize = 20.sp , color = Color.Black, textDecoration = TextDecoration.Underline)
                    if (openDialog.value){
                        CreateDialogChangeName()
                    }

                }
            }

            @Composable
            fun messageForm(name:String,time:Long, messageText:String){
               // var currentAlign =  remember {mutableStateOf(Alignment.End) }
//                if (userId==viewModel.id){ // Start - расположение слева  End - Расположение справо
//                    Log.d("PPPPPPPP", currentAlign.value.toString())
//                    Log.d("PPPPPPPP", userId)
//                    Log.d("PPPPPPPP", viewModel.id)
//                    currentAlign.value = Alignment.End
//                }else{
//                    Log.d("PPPP=", currentAlign.value.toString())
//                    Log.d("PPPP=", userId)
//                    Log.d("PPPP=", viewModel.id)
//                    currentAlign.value = Alignment.Start
//                }

                Box( modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(0.45f)
                    .wrapContentHeight()
                    .background(color = Color(0xFF0066FF))
                     ){
                    Column()
                    {
                        Box( modifier = Modifier
                            .fillMaxWidth()
                         ) {   //textStyle = TextStyle(),
                            Text(lineHeight = TextUnit(10f, TextUnitType.Sp),modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp),text = name, style = TextStyle(color = Color(0xFFF1F1F9)),  fontSize = 10.sp)
                            Text(lineHeight = TextUnit(10f, TextUnitType.Sp),modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 5.dp, top = 5.dp), style = TextStyle(color = Color(0xFFF1F1F9)), text = formatTime( time ), fontSize = 10.sp)
                        }
                        Text(lineHeight = TextUnit(20f, TextUnitType.Sp),modifier = Modifier.padding(start = 5.dp, bottom = 5.dp), style = TextStyle(color = Color(0xFFF1F1F9)), text = messageText,fontSize = 17.sp,)
                    }
                }


            }
            val lazyColumnState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            val listOfMessage by viewModel.newListOfMessages.observeAsState()
            val shouldScroll by viewModel.shouldScroll.observeAsState()
            LazyColumn(state = lazyColumnState,modifier = Modifier
                .fillMaxWidth()
                .weight(7f)
                .background(Color(0xFF0FBFFF))
                //.verticalScroll(rememberScrollState(), enabled = true)
            ) {
                if (shouldScroll!!){
                    coroutineScope.launch() {
                        lazyColumnState.animateScrollToItem(listOfMessage!!.size-1)
                        viewModel.shouldScroll.value = false
                    }
                }


                if (listOfMessage!=null){

                    items(listOfMessage!!){
                            message -> Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween )
                    {
                        var flag = true
                        if (viewModel.id != message.id){
                            flag = false
                        }
                        Box(){
                            if (!flag){
                                messageForm(name = message.name, time = message.time , messageText = message.text  )
                            }
                        }
                        Box(){
                            if (flag){
                                messageForm(name = message.name, time = message.time, messageText = message.text  )
                            }
                        }
                    }

                    }
                }

            }

            Row(modifier = Modifier
                .fillMaxSize()
                .weight(1f), horizontalArrangement = Arrangement.SpaceEvenly){
                var message =  remember{mutableStateOf("")}
                Box(modifier = Modifier
                    .weight(4f)
                    .padding(15.dp)){
                    TextField(value = message.value, onValueChange ={newText-> message.value = newText}, singleLine = false,
                        modifier = Modifier
                            .fillMaxSize()
                            .onKeyEvent {
                                if (it.key == Key.Enter) {
                                    // Добавляем новую строку в текстовое поле
                                    message.value += "\n"
                                    true // возвращаем true для предотвращения дальнейшей обработки
                                } else {
                                    false
                                }
                            }
                    )
                }


                Box(modifier = Modifier.weight(1f)) {
                    IconButton(
                        onClick = {
                            //myRef.push().setValue(message.value)
                            Log.d("KKKKKKKKKKKKK", viewModel.userName)
                            if(message.value.trim()!=""){
                                var newMessage = ChatMessage(name = viewModel.userName, text = message.value.trim(), id = viewModel.id)

                                val messageId = myRef.push().key // Генерация уникального идентификатора для сообщения

                                if (messageId != null) { // Проверьте, что ID не null
                                    myRef.child(messageId).setValue(newMessage)

                                }


                            }



                            message.value = ""
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .size(40.dp)
                    ) {
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = "Massage",
                            modifier = Modifier
                                .size(40.dp)
                        )

                    }

                }

            }

        }
    }





    fun Greg(){
        Log.d("12","123")
    }

}








//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestChatTheme {
//        var count = remember {
//            mutableSta teOf(0)
//        }
//        var str = "cliks: ${count.value}"
//        val modifier1 = Modifier
//            .size(200.dp)
//            .background(Color(22, 219, 42), shape = RectangleShape)
//            .verticalScroll(rememberScrollState(), enabled = true)
//            .clickable(onClick = {
//                count.value += 1
//                Log.d("1", count.toString())
//            })
//
//
//
//        val modifier2 = Modifier
//            .padding(horizontal = 70.dp, vertical = 200.dp)
//
//        Row() {
//
//            Greeting(str, modifier1 )
//            NameSpace(name = "Greg",modifier2)
//        }
//    }
//}
//
//@Composable
//fun NameSpace(name: String, modifier: Modifier){
//    Text(
//        text = "$name",
//        modifier = modifier
//    )
//}