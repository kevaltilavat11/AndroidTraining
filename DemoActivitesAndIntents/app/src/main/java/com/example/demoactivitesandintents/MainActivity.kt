package com.example.demoactivitesandintents

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var navigateButton: Button
    private val lifecycleLogs = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lifecycleListView)
        navigateButton = findViewById(R.id.btnNavigate)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lifecycleLogs)
        listView.adapter = adapter

        logEvent("App in onCreate state")
        Toast.makeText(this, "onCreate finished", Toast.LENGTH_SHORT).show()

        navigateButton.setOnClickListener {
            val intent = Intent(this, ShowActivityData::class.java)
            intent.putStringArrayListExtra("logs", ArrayList(lifecycleLogs))
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        logEvent("App in onStart state")
    }

    override fun onResume() {
        super.onResume()
        logEvent("App in onResume state")
    }

    override fun onPause() {
        super.onPause()
        logEvent("App in onPause state")
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        logEvent("App in onStop state")
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        logEvent("App in onDestroy state")
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    private fun logEvent(event: String) {
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$event at $timestamp"
        lifecycleLogs.add(log)
        (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}
