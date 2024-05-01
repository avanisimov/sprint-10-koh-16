package ru.yapracticum.sprint10koh16

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.IllegalStateException

class ChatMessagesAdapter : RecyclerView.Adapter<ViewHolder>() {

    var items: List<ChatMessage> = emptyList()
        set(value) {
            val oldItems = field
            val newItems = value.toMutableList()

            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return oldItems.size
                }

                override fun getNewListSize(): Int {
                    return newItems.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return oldItems[oldItemPosition].id == newItems[newItemPosition].id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return oldItems[oldItemPosition] == newItems[newItemPosition]
                }

            })
            field = newItems
            diffResult.dispatchUpdatesTo(this)
//            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            VIEW_TYPE_MY_MESSAGE -> {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.v_my_chat_message, parent, false)
                return MyChatMessageViewHolder(itemView).also {
                    Log.d("SPRINT_10", "onCreateViewHolder $it")
                }
            }

            VIEW_TYPE_OTHER_MESSAGE -> {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.v_other_chat_message, parent, false)
                return OtherChatMessageViewHolder(itemView).also {
                    Log.d("SPRINT_10", "onCreateViewHolder $it")
                }
            }
            VIEW_TYPE_CHAT_DATE -> {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.v_chat_date, parent, false)
                return ChatDateViewHolder(itemView).also {
                    Log.d("SPRINT_10", "onCreateViewHolder $it")
                }
            }

            else -> throw IllegalStateException("There is no ViewHolder for type=$viewType")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ChatMessage.MyChatMessage -> VIEW_TYPE_MY_MESSAGE
            is ChatMessage.OtherChatMessage -> VIEW_TYPE_OTHER_MESSAGE
            is ChatMessage.ChatDate -> VIEW_TYPE_CHAT_DATE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("SPRINT_10", "onBindViewHolder position=$position $holder")
        when (val item = items[position]) {
            is ChatMessage.MyChatMessage -> {
                (holder as MyChatMessageViewHolder).bind(item)
            }

            is ChatMessage.OtherChatMessage -> {
                (holder as OtherChatMessageViewHolder).bind(item)
            }

            is ChatMessage.ChatDate ->  {
                (holder as ChatDateViewHolder).bind(item)
            }
        }

    }

    companion object {
        const val VIEW_TYPE_MY_MESSAGE = 0
        const val VIEW_TYPE_OTHER_MESSAGE = 1
        const val VIEW_TYPE_CHAT_DATE = 2
    }


}