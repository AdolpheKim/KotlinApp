package com.example.kotlin_app.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import de.hdodenhof.circleimageview.CircleImageView

class ChatOtherViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    val textViewChat: TextView
    val textViewTime: TextView
    val ImageViewIcon : CircleImageView

    init {
        textViewChat = v.findViewById(R.id.textViewChat2)
        textViewTime = v.findViewById(R.id.textViewTime2)
        ImageViewIcon = v.findViewById(R.id.ImageViewIcon2)
    }
}