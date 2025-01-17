package com.example.userinteraction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    val bookName = intent.getStringExtra("BOOK_NAME")
//    val authorName = intent.getStringExtra("AUTHOR_NAME")
//    val selectedDate = intent.getStringExtra("SELECTED_DATE")
//    val genre = intent.getStringExtra("GENRE")
//    val isFiction = intent.getBooleanExtra("IS_FICTION", false)
//    val isNonFiction = intent.getBooleanExtra("IS_NON_FICTION", false)
//    val ageGroupsArray = intent.getStringArrayExtra("IS_AGE_GROUP_SELECTED")
}