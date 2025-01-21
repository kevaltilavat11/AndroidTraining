package com.example.mytodobook

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_details)

        val bookTitle = intent.getStringExtra("BOOK_TITLE") ?: "N/A"
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR") ?: "N/A"
        val genre = intent.getStringExtra("GENRE") ?: "N/A"
        val ageGroups = intent.getStringExtra("AGE_GROUPS") ?: "N/A"
        val faction = intent.getStringExtra("FACTION") ?: "N/A"

        val tvBookTitle: TextView = findViewById(R.id.tvBookTitle)
        val tvBookAuthor: TextView = findViewById(R.id.tvBookAuthor)
        val tvGenre: TextView = findViewById(R.id.tvGenre)
        val tvAgeGroups: TextView = findViewById(R.id.tvAgeGroups)
        val tvFaction: TextView = findViewById(R.id.tvFaction)

        tvBookTitle.text = bookTitle
        tvBookAuthor.text = bookAuthor
        tvGenre.text = genre
        tvAgeGroups.text = ageGroups
        tvFaction.text = faction

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}