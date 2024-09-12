package com.example.testchat

import ChatMessage
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database


fun generateId():String{
    val symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    var id = ""
    repeat(10){
        id+=symbols.random()
    }
    return id
}

class ViewModelChat: ViewModel() {
    val listOfMessages = mutableStateListOf<ChatMessage>()
    var userName = "noName"
    var  id = ""

    fun addMessage(message: ChatMessage) {
        listOfMessages.add(message)
    }

    val newListOfMessages = MutableLiveData<List<ChatMessage>>()
    var shouldScroll  = MutableLiveData<Boolean>(false)




    val database = Firebase.database("https://testchat-b9f77-default-rtdb.europe-west1.firebasedatabase.app")
    val myRef = database.getReference("messages")
    private var childEventListener: ChildEventListener

    init {
        var auth = Firebase.auth.currentUser
        id = auth!!.uid




        childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // Этот метод вызывается для каждого дочернего элемента в указанном узле,
                // когда он добавляется или когда слушатель впервые инициализируется.
                //val viewModel: ViewModelChat =  viewModel( LocalViewModelStoreOwner.current!!)
                // Преобразование dataSnapshot в объект ChatMessage
                val newMessage = dataSnapshot.getValue(ChatMessage::class.java)

                // Проверка на null и вывод в лог
                if (newMessage != null) {
                    try {
                        addMessage(newMessage)
                        newListOfMessages.value = listOfMessages
                        shouldScroll.value = true

                        Log.d("NewMessage", "New message from: ${newMessage}")
                    }catch (e:Exception){

                    }

                }
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // Реализация для случаев изменения дочернего элемента (если необходимо)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                // Реализация для случаев удаления дочернего элемента (если необходимо)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // Реализация для случаев перемещения дочернего элемента (если необходимо)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибок
                Log.e("DatabaseError", "Error: ${databaseError.message}")
            }
        }

        myRef.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        myRef.removeEventListener(childEventListener)
        // Удалите слушатель при уничтожении ViewModel
    }


}