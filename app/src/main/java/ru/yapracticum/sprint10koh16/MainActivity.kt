package ru.yapracticum.sprint10koh16

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date
import java.util.LinkedList
import java.util.UUID

/*
 -
 */
class MainActivity : AppCompatActivity() {

    var messages = LinkedList<ChatMessage>(
        listOf(
            ChatMessage.MyChatMessage(
                id = UUID.randomUUID().toString(),
                date = Date(),
                text = "Hope u r well?",
                status = MessageStatus.NEW,
            ),
            ChatMessage.MyChatMessage(
                id = UUID.randomUUID().toString(),
                date = Date(),
                text = "How are you?",
                status = MessageStatus.SENT,
            ),
            ChatMessage.MyChatMessage(
                id = UUID.randomUUID().toString(),
                date = Date(),
                text = "Hi",
                status = MessageStatus.READ,
            ),
            ChatMessage.ChatDate(
                id = UUID.randomUUID().toString(),
                date = Date(),
                text = "Today"
            ),
        )
    )
    val chatMessagesAdapter = ChatMessagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val rvItems: RecyclerView = findViewById(R.id.rvItems)
        rvItems.apply {
            adapter = chatMessagesAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
        }

        val etMessage: EditText = findViewById(R.id.etMessage)
        val ivAction: ImageView = findViewById(R.id.ivAction)

        etMessage.doAfterTextChanged {
            ivAction.setImageResource(
                if (it.isNullOrBlank()) {
                    R.drawable.ic_audio
                } else {
                    R.drawable.ic_send
                }
            )
        }

        chatMessagesAdapter.items = messages

        ivAction.setOnClickListener {
            val messageText = etMessage.text?.toString()
            if (!messageText.isNullOrBlank()) {
                val newMessage = ChatMessage.MyChatMessage(
                    id = UUID.randomUUID().toString(),
                    date = Date(),
                    text = messageText,
                    status = MessageStatus.NEW,
                )
                messages.add(0, newMessage)
                rvItems.scrollToPosition(0)
                chatMessagesAdapter.items = messages
                etMessage.setText("")

//                db.collection("chats").document("chat")
//                    .set(city)
//                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }




                rvItems.postDelayed({
                    messages.add(
                        0,
                        ChatMessage.OtherChatMessage(
                            id = UUID.randomUUID().toString(),
                            date = Date(),
                            text = "Ok, $messageText",
                        )
                    )
                    chatMessagesAdapter.items = messages
                    rvItems.scrollToPosition(0)
                }, 1000L)
            }
        }
    }
}