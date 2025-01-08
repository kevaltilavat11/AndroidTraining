package com.example.demoactivitesandintents

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*
class MainActivity : AppCompatActivity() {
    private lateinit var textViewLog: TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewLog = findViewById(R.id.textViewLog)
        logEvent("App in onCreate state")
        Toast.makeText(this, "onCreate finished", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        logEvent("App in onStart state")
        Toast.makeText(this, "onStart finished", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        logEvent("App in onResume state")
        Toast.makeText(this, "onResume finished", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        logEvent("App in onPause state")
        Toast.makeText(this, "onPause finished", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        logEvent("App in onStop state")
        Toast.makeText(this, "onStop finished", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        logEvent("App in onDestroy state")
        Toast.makeText(this, "onDestroy finished", Toast.LENGTH_SHORT).show()
    }

    private fun logEvent(event: String) {
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        textViewLog.append("$event at \n\n$timestamp\n\n")
    }

}