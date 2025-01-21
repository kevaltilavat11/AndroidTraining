package com.example.mytodobook

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        val tvBookHeader: TextView = findViewById(R.id.header)
        val tvBookTitle: TextView = findViewById(R.id.tvBookTitle)
        val tvBookAuthor: TextView = findViewById(R.id.tvBookAuthor)
        val tvGenre: TextView = findViewById(R.id.tvGenre)
        val tvAgeGroups: TextView = findViewById(R.id.tvAgeGroups)
        val tvFaction: TextView = findViewById(R.id.tvFaction)
        tvBookHeader.text = bookTitle
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

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun editBookDetail(view: View) {

        val bookId = intent.getStringExtra("BOOK_ID")
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
        val intent = Intent(this, AddBookActivity::class.java)
        intent.putExtra("EDIT_MODE", true)
        intent.putExtra("BOOK_ID", bookId)
        intent.putExtra("BOOK_TITLE", bookTitle)
        intent.putExtra("BOOK_AUTHOR", bookAuthor)
        intent.putExtra("GENRE", genre)
        intent.putExtra("AGE_GROUPS", ageGroups)
        intent.putExtra("FACTION", faction)
        startActivity(intent)
    }

}