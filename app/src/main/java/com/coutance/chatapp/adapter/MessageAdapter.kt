package com.coutance.chatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coutance.chatapp.databinding.ReceiveBinding
import com.coutance.chatapp.databinding.SentBinding
import com.coutance.chatapp.model.MessageModel
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(private val context: Context) :
    ListAdapter<MessageModel, RecyclerView.ViewHolder>(MessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_RECEIVE) {
            val binding = ReceiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceiveViewHolder(binding)
        } else {
            val binding = SentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentViewHolder(binding)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = getItem(position)

        if (holder is SentViewHolder) {
            holder.sentMessage.text = currentMessage.message
        } else if (holder is ReceiveViewHolder) {
            holder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = getItem(position)
        return if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)) {
            ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    inner class SentViewHolder(private val binding: SentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val sentMessage: TextView = binding.tvSentMessage
    }

    inner class ReceiveViewHolder(private val binding: ReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val receiveMessage: TextView = binding.tvReceiveMessage
    }

    companion object {
        private const val ITEM_RECEIVE = 1
        private const val ITEM_SENT = 2
    }
}