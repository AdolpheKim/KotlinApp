package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth : FirebaseAuth
    private lateinit var buttonSignIn : Button
    private lateinit var textEmail : EditText
    private lateinit var textPassword : EditText
    private lateinit var textSignUp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth

        buttonSignIn = findViewById(R.id.buttonSignIn)
        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)
        textSignUp = findViewById(R.id.textSignUp)

        buttonSignIn.setOnClickListener(this)
        textSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val email : String? = textEmail.text?.toString()
        val password : String? = textPassword.text?.toString()

        if(v?.id == R.id.buttonSignIn){
            auth.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val newIntent = Intent(this, FriendsListActivity::class.java)
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newIntent)
                    }
                }
        }
        else if(v?.id == R.id.textSignUp){
            val newIntent = Intent(this, SignUpActivity::class.java)

            startActivity(newIntent)
        }
    }
}