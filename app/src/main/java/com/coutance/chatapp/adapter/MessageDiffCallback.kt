package com.coutance.chatapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.coutance.chatapp.model.MessageModel

class MessageDiffCallback : DiffUtil.ItemCallback<MessageModel>() {
    override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        return oldItem == newItem
    }
}