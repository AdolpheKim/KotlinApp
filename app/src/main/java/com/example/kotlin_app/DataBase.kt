package com.example.kotlin_app

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBase {
    private lateinit var userDate : MutableMap<String, Map<String, Any>>
    private lateinit var userList : MutableMap<String, Map<String, Any>>
    private lateinit var message : MutableMap<String, Map<String, Any>>
    private lateinit var roomMember : MutableMap<String, Map<String, Any>>

    private val store = Firebase.firestore
    private val auth = Firebase.auth

    public fun InitData(){
        store.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    userDate.set("", i.getData())
                }
            }

        store.collection("UserList")
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    userList.set("", i.getData())
                }
            }

        store.collection("Message")
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    userList.set("", i.getData())
                }
            }

        store.collection("RoomMember")
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    userList.set("", i.getData())
                }
            }
    }
}