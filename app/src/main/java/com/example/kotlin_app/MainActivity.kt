package com.example.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity (), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSignIn = findViewById<Button>(R.id.buttonLogIn)
        val textViewSignUp = findViewById<TextView>(R.id.textViewSignUp)

        buttonSignIn.setOnClickListener(this)
        textViewSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.buttonLogIn) {
            val newIntent = Intent(this, SignInActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent)
        }
        else if(v?.id == R.id.textViewSignUp) {
            val newIntent = Intent(this, SignUpActivity::class.java)
            startActivity(newIntent)
        }
    }
}
