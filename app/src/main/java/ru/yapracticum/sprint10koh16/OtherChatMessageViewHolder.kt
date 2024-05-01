package ru.yapracticum.sprint10koh16

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class OtherChatMessageViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(
    itemView
) {

    val tvText: TextView = itemView.findViewById(R.id.tvText)
    val tvDate: TextView = itemView.findViewById(R.id.tvDate)

    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun bind(item: ChatMessage.OtherChatMessage) {
        tvText.text = item.text
        tvDate.text = formatter.format(item.date)

    }
}