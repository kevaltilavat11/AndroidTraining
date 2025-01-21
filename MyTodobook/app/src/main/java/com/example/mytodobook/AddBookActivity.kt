package com.example.mytodobook

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
        val genreSpinner = findViewById<Spinner>(R.id.spGenre)
        val rbFiction = findViewById<RadioButton>(R.id.rbFiction)
        val rbNonFiction = findViewById<RadioButton>(R.id.rbNonFiction)
        val checkBoxes = listOf(
            R.id.cbChildren to "Children",
            R.id.cbTeens to "Teens",
            R.id.cbAdults to "Adults"
        )

        val bookNameText = bookName.text.toString().trim()
        val authorNameText = authorName.text.toString().trim()

        when {
            bookNameText.isEmpty() && authorNameText.isEmpty() -> {
                bookName.error = getString(R.string.valid_bookName)
                authorName.error = getString(R.string.valid_authorName)
            }
            bookNameText.isEmpty() -> {
                bookName.error = getString(R.string.valid_bookName)
            }
            authorNameText.isEmpty() -> {
                authorName.error = getString(R.string.valid_authorName)
            }
            else -> {
                bookList.add(
                    Book(
                        bookNameText,
                        authorNameText,
                        selectedDate.text.toString(),
                        genreSpinner.selectedItem.toString(),
                        if (rbFiction.isChecked) "Fiction" else "Non-Fiction",
                        checkBoxes.filter { findViewById<CheckBox>(it.first).isChecked }
                            .map { it.second }
                    )
                )

                Intent(this, BookListActivity::class.java).also {
                    startActivity(it)
                }

                resetFields(bookName, authorName, selectedDate, genreSpinner, checkBoxes, rbFiction, rbNonFiction)
            }
        }
    }

    private fun resetFields(
        bookName: EditText,
        authorName: EditText,
        selectedDate: EditText,
        genreSpinner: Spinner,
        checkBoxes: List<Pair<Int, String>>,
        rbFiction: RadioButton,
        rbNonFiction: RadioButton
    ) {
        bookName.text.clear()
        authorName.text.clear()
        selectedDate.text.clear()

        genreSpinner.setSelection(0)

        checkBoxes.forEach {
            findViewById<CheckBox>(it.first).isChecked = false
        }

        rbFiction.isChecked = false
        rbNonFiction.isChecked = false

        bookName.error = null
        authorName.error = null
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