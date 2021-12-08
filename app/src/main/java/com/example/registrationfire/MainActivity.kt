package com.example.registrationfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var editEmail : TextView
    private lateinit var editPass1 : TextView
    private lateinit var editPass2 : TextView
    private lateinit var button : Button
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        registerList()
    }
    private fun init(){
        editEmail = findViewById(R.id.editEmail)
        editPass1 = findViewById(R.id.editPass1)
        editPass2 = findViewById(R.id.editPass2)
        button = findViewById(R.id.button)
    }

    private fun registerList() {
        button.setOnClickListener {
            val editEm = editEmail.text.toString()
            val editPas = editPass1.text.toString()
            val editPas1 = editPass2.text.toString()


            if (!(editEm.matches(emailPattern.toRegex())) && !(editEm.matches(emailPattern2.toRegex()))){
                editEmail.error = "Incorrect Email"
                return@setOnClickListener
            }
            if (editPas.count()<9 || editPas.isEmpty()){
                editPass1.error = "Enter Password"
                return@setOnClickListener
            }
            if(editPas.isDigitsOnly()){
                editPass1.error = "Must contain letters"
                return@setOnClickListener
            }
            if (!(editPas.matches(".*[A-Z].*".toRegex())) && !(editPas.matches(".*[a-z].*".toRegex())) ){
                editPass1.error = "Must contain letters"
                return@setOnClickListener
            }
            if (!(editPas.matches(".*[0-9].*".toRegex()))){
                editPass1.error = "Must contain digits"
                return@setOnClickListener
            }
            if (!(editPas.matches(".*[$@#!?_].*".toRegex()))){
                editPass1.error = "Must contain special symbols '$@#!?_'"
            }
            if (editPas1 != editPas){
                editPass2.error = "Passwords don't match"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(editEm,editPas).addOnCompleteListener{ task-> if(task.isSuccessful) {
                Toast.makeText(this, "You registered successfully", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()}





        }
    }
}
