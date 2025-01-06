package com.example.myfirstapp

import com.example.myfirstapp.R
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val agePicker = findViewById<NumberPicker>(R.id.age_picker)
        agePicker.minValue = 18  // Set minimum value
        agePicker.maxValue = 100 // Set maximum value
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
        if (usernameText.isEmpty() && emailText.isEmpty() && contactNumberText.isEmpty() && contactNumberText.isEmpty()) {
            username.setError(getString(R.string.valid_fields));
            email.setError(getString(R.string.valid_fields))
            password.setError(getString(R.string.valid_fields))
            contactNumber.setError(getString(R.string.valid_fields))
        } else if (usernameText.isEmpty()) {
            username.setError(getString(R.string.valid_username));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError(getString(R.string.valid_email))
        } else if (passwordText.length < 6) {
            password.setError(getString(R.string.valid_password))
        } else if (contactNumberText.length != 10) {
            contactNumber.setError(getString(R.string.valid_contactNumber))
        } else {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

}