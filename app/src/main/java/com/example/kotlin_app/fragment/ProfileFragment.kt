package com.example.kotlin_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin_app.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var imageViewIcon: ImageView

    private val store = Firebase.firestore
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val currentUser = auth.currentUser?.email

        textViewName = v.findViewById(R.id.textViewName3)
        imageViewIcon = v.findViewById(R.id.imageViewProfile)
        store.collection("UserData")
            .document(currentUser!!)
            .get()
            .addOnSuccessListener { result ->
                textViewName.text = result["name"].toString()
            }
        return v
    }

     fun newInstant() : ProfileFragment {
        val args = Bundle()
        val fragment = ProfileFragment()
        fragment.arguments = args

        return fragment
    }
}