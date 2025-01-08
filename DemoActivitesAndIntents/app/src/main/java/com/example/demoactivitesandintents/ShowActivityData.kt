package com.example.demoactivitesandintents

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ShowActivityData : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var shareButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gridView = findViewById(R.id.lifecycleGridView)
        shareButton = findViewById(R.id.btnShare)

        val lifecycleLogs = intent.getStringArrayListExtra("logs") ?: emptyList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lifecycleLogs)
        gridView.adapter = adapter

        shareButton.setOnClickListener {
            shareLogs(lifecycleLogs)
        }
    }
    private fun shareLogs(logs: List<String>) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, logs.joinToString("\n"))
        }
        startActivity(Intent.createChooser(shareIntent, "Share logs via"))
    }
}