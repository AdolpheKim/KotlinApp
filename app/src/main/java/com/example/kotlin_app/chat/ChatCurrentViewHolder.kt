package com.example.kotlin_app.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import de.hdodenhof.circleimageview.CircleImageView

class ChatCurrentViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    val textViewChat: TextView
    val textViewTime: TextView
    val ImageViewIcon : CircleImageView

    init {
        textViewChat = v.findViewById(R.id.textViewChat)
        textViewTime = v.findViewById(R.id.textViewTime)
        ImageViewIcon = v.findViewById(R.id.ImageViewIcon)
    }
}