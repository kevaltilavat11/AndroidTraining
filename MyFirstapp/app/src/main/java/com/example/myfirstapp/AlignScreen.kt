package com.example.myfirstapp

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.isVisible

class AlignScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_align_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun topClick (view:View?){
        val bottomContainer = findViewById<RelativeLayout>(R.id.bottom_container)
        bottomContainer.visibility = View.INVISIBLE
    }
    fun centerClick (view:View?){
        val topContainer = findViewById<RelativeLayout>(R.id.top_container)
        val bottomContainer = findViewById<RelativeLayout>(R.id.bottom_container)
        topContainer.visibility = View.VISIBLE
        bottomContainer.visibility = View.VISIBLE
    }
    fun bottomClick (view:View?){
        val topContainer = findViewById<RelativeLayout>(R.id.top_container)
        topContainer.visibility = View.INVISIBLE
    }
}