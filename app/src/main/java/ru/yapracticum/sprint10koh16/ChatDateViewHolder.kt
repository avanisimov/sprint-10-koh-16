package ru.yapracticum.sprint10koh16

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class ChatDateViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(
    itemView
) {

    val tvText: TextView = itemView.findViewById(R.id.tvText)


    fun bind(item: ChatMessage.ChatDate) {
        tvText.text = item.text

    }
}