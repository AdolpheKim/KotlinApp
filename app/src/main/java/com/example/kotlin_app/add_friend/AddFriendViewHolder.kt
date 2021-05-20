package com.example.kotlin_app.add_friend

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import de.hdodenhof.circleimageview.CircleImageView

class AddFriendViewHolder(v : View) : RecyclerView.ViewHolder(v) {

    val textView: TextView
    val circleImageView : CircleImageView
    val ImageView : ImageView

    init {
        textView = v.findViewById(R.id.textViewName2)
        circleImageView = v.findViewById(R.id.ImageViewProfile2)
        ImageView = v.findViewById(R.id.imageViewAddFriend)
    }

}