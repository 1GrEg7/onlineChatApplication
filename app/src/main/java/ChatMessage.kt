
data class ChatMessage(
    val name:String = "",
    val text:String = "",
    val time:Long = System.currentTimeMillis(),
    val id: String = ""
)
