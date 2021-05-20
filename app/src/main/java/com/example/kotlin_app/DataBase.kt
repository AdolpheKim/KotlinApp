package com.example.kotlin_app

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBase {
    private lateinit var userDate : Map<String, Map<String, Any>>
    private lateinit var userList : Map<String, Map<String, Any>>
    private lateinit var message : Map<String, Map<String, Any>>
    private lateinit var roomMember : Map<String, Map<String, Any>>

    private val store = Firebase.firestore
    private val auth = Firebase.auth

    public fun Init(){
        store.collection("UserData")
            .get()

        store.collection("UserList")
            .get()

        store.collection("Message")
            .get()

        store.collection("RoomMember")
            .get()
    }
}