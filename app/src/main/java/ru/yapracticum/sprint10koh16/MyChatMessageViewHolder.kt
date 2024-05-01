package ru.yapracticum.sprint10koh16

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class MyChatMessageViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(
    itemView
) {

    val tvText: TextView = itemView.findViewById(R.id.tvText)
    val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    val ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)

    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun bind(item: ChatMessage.MyChatMessage) {
        tvText.text = item.text
        tvDate.text = formatter.format(item.date)
        ivStatus.setImageResource(
            when (item.status) {
                MessageStatus.NEW -> R.drawable.ic_new
                MessageStatus.SENT -> R.drawable.ic_sent
                MessageStatus.READ -> R.drawable.ic_read
            }
        )
    }
}