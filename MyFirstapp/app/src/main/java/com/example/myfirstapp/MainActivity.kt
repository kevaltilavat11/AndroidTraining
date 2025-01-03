package com.example.myfirstapp

import com.example.myfirstapp.R
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun validateDetails(view: View?) {
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.user_email)
        val password = findViewById<EditText>(R.id.user_password)
        val contactNumber = findViewById<EditText>(R.id.contact_number)
        val agePicker = findViewById<NumberPicker>(R.id.age_picker)
        val usernameText = username.text.toString().trim { it <= ' ' }
        val emailText = email.text.toString().trim { it <= ' ' }
        val passwordText = password.text.toString().trim { it <= ' ' }
        val contactNumberText = contactNumber.text.toString().trim { it <= ' ' }
        val age = agePicker.value
        if (usernameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || contactNumberText.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            Toast.makeText(this, "Invalid Email Address!", Toast.LENGTH_SHORT).show()
        } else if (passwordText.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters!", Toast.LENGTH_SHORT)
                .show()
        } else if (contactNumberText.length != 10) {
            Toast.makeText(this, "Contact Number must be 10 digits!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Details are valid!", Toast.LENGTH_SHORT).show()
        }
    }

}