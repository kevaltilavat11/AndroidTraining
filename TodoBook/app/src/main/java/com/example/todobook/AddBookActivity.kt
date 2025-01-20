package com.example.todobook

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Book(
    val bookName: String,
    val authorName: String,
    val date: String,
    val genre: String,
    val isFiction: String,
    val ageGroups: List<String>
)
class AddBookActivity : AppCompatActivity() {

    private lateinit var selectDate: EditText
    companion object {
        val bookList = mutableListOf<Book>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_book)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun navigateToBookListScreen(view: View) {
        val bookName = findViewById<EditText>(R.id.etBookName)
        val authorName = findViewById<EditText>(R.id.etAuthorName)
        val selectedDate = findViewById<EditText>(R.id.etDate)
        val genre = findViewById<Spinner>(R.id.spGenre).selectedItem.toString()
        val isFiction = if (findViewById<RadioButton>(R.id.rbFiction).isChecked) "Fiction" else "Non-Fiction"
        val selectedAgeGroups = mutableListOf<String>()

        if (findViewById<CheckBox>(R.id.cbChildren).isChecked) {
            selectedAgeGroups.add("Children")
        }
        if (findViewById<CheckBox>(R.id.cbTeens).isChecked) {
            selectedAgeGroups.add("Teens")
        }
        if (findViewById<CheckBox>(R.id.cbAdults).isChecked) {
            selectedAgeGroups.add("Adults")
        }

        val bookNameText = bookName.text.toString().trim()
        val authorNameText = authorName.text.toString().trim()

        if (bookNameText.isEmpty() && authorNameText.isEmpty()) {
            bookName.error = getString(R.string.valid_bookName)
            authorName.error = getString(R.string.valid_authorName)
        } else if (bookNameText.isEmpty()) {
            bookName.error = getString(R.string.valid_bookName)
        } else if (authorNameText.isEmpty()) {
            authorName.error = getString(R.string.valid_authorName)
        } else {
            bookList.add(
                Book(
                    bookNameText,
                    authorNameText,
                    selectedDate.text.toString(),
                    genre,
                    isFiction,
                    selectedAgeGroups
                )
            )
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }


    fun showDatePicker(view: View) {
        selectDate = findViewById(R.id.etDate)
        val currentDate = Calendar.getInstance()
        val currentYear = currentDate.get(Calendar.YEAR)
        val currentMonth = currentDate.get(Calendar.MONTH)
        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                selectDate.setText(formattedDate.toString())
            },
            currentYear,
            currentMonth,
            currentDay
        )
        datePickerDialog.show()
    }

}