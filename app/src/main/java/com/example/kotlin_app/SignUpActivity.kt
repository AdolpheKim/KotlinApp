package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var btnSignUp : Button
    private lateinit var textName : EditText
    private lateinit var textEmail : EditText
    private lateinit var textPassword : EditText
    private lateinit var textConfirmPassword : EditText
    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog

    private val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
    private val store : FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        btnSignUp = findViewById(R.id.buttonSignIn)
        textName = findViewById(R.id.editTextPersonName)
        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)
        textConfirmPassword = findViewById(R.id.editTextConfirmPassword)

        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val name : String? = textName.text?.toString()
        val email : String? = textEmail.text?.toString()
        val password : String? = textPassword.text?.toString()
        val confirmPassword: String? = textConfirmPassword.text?.toString()
        val defaultImg : String = "R.drawable.profile"
        val friends : MutableList<String>? = mutableListOf()
        val roomName : MutableList<String>? = mutableListOf()

        var addUserData : Boolean = false

        builder = AlertDialog.Builder(this)
        dialog = builder.create()

        if(!confirmAllRules(name, email, password, confirmPassword)){
            return
        }

        val userData = hashMapOf(
                "email" to email,
                "image" to defaultImg,
                "name" to name
        )

        store.collection("UserData")
            .document(email!!)
            .set(userData)
            .addOnSuccessListener {
                addUserData = true
            }

        auth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && addUserData) {
                    val newIntent = Intent(this, MainActivity::class.java)
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(newIntent)
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w("??????", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun confirmAllRules(name : String?, email : String?, password : String?, confirmPassword: String?) : Boolean{

        if(name.isNullOrEmpty()){
            builder.setTitle("Error").setMessage("????????? ???????????? ?????????.").show()
            return false
        }
        if(email.isNullOrEmpty()){
            builder.setTitle("Error").setMessage("???????????? ???????????? ?????????").show()
            return false
        }
        if( !emailPattern.matcher(email).matches() ){
            builder.setTitle("Error").setMessage("????????? ????????? ?????? ??????????????????.").show()
            return false
        }
        if(password?.length!! < 8) {
            builder.setTitle("Error").setMessage("??????????????? 8?????? ??????????????? ?????????.").show()
            return false
        }
        if(!password.equals(confirmPassword)){
            builder.setTitle("Error").setMessage("??????????????? ???????????? ????????????.").show()
            return false
        }

        return true
    }

}