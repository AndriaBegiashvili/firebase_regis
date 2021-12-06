package com.example.registrationfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var editEmail : TextView
    private lateinit var editPass1 : TextView
    private lateinit var editPass2 : TextView
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        registerlis()
    }
    private fun init(){
        editEmail = findViewById(R.id.editEmail)
        editPass1 = findViewById(R.id.editPass1)
        editPass2 = findViewById(R.id.editPass2)
        button = findViewById(R.id.button)
    }

    private fun registerlis() {
        button.setOnClickListener {
            val editEm = editEmail.text.toString()
            val editPas = editPass1.text.toString()
            val editPas1 = editPass2.text.toString()

            if (editEm.isEmpty() || '@' !in editEm || '.' !in editEm){
                editEmail.error = "Enter correct E-mail"
                return@setOnClickListener
            }
            else if (editPas.count()<8 || editPas.isEmpty()){
                editPass1.error = "Enter Password"
                return@setOnClickListener
            }
            else if (editPas1 != editPas){
                editPass2.error = "Passwords don't match"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(editEm,editPas).addOnCompleteListener{ task-> if(task.isSuccessful){
                Toast.makeText(this, "You registered successfully", Toast.LENGTH_SHORT).show()
            } }




        }
    }
}