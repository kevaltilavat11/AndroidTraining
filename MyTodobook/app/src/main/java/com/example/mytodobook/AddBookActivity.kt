package com.example.mytodobook

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
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

    private var editingBookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_book)

        val etBookName = findViewById<EditText>(R.id.etBookName)
        val etAuthorName = findViewById<EditText>(R.id.etAuthorName)
        val etDate = findViewById<EditText>(R.id.etDate)
        val spGenre = findViewById<Spinner>(R.id.spGenre)
        val rbFiction = findViewById<RadioButton>(R.id.rbFiction)
        val rbNonFiction = findViewById<RadioButton>(R.id.rbNonFiction)
        val btnSave = findViewById<Button>(R.id.btnAddBook)
        val tvHeader = findViewById<TextView>(R.id.header)

        val checkBoxes = listOf(
            R.id.cbChildren to "Children",
            R.id.cbTeens to "Teens",
            R.id.cbAdults to "Adults"
        )

        val editMode = intent.getBooleanExtra("EDIT_MODE", false)
        if (editMode) {
            editingBookId = intent.getStringExtra("BOOK_ID")
            val bookToEdit = bookList.find { it.id == editingBookId }
            bookToEdit?.let { book ->
                etBookName.setText(book.bookName)
                etAuthorName.setText(book.authorName)
                etDate.setText(book.date)

                val genreAdapter = spGenre.adapter
                for (i in 0 until genreAdapter.count) {
                    if (genreAdapter.getItem(i).toString() == book.genre) {
                        spGenre.setSelection(i)
                        break
                    }
                }

                if (book.isFiction == "Fiction") {
                    rbFiction.isChecked = true
                } else {
                    rbNonFiction.isChecked = true
                }

                val selectedAgeGroups = book.ageGroups
                checkBoxes.forEach { (id, label) ->
                    findViewById<CheckBox>(id).isChecked = selectedAgeGroups.contains(label)
                }
            }
            tvHeader.text = getString(R.string.update_book)
            btnSave.text = getString(R.string.update_book)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
                val updatedBook = Book(
                    id = editingBookId ?: UUID.randomUUID().toString(),
                    bookName = bookNameText,
                    authorName = authorNameText,
                    date = selectedDate.text.toString(),
                    genre = genreSpinner.selectedItem.toString(),
                    isFiction = if (rbFiction.isChecked) "Fiction" else "Non-Fiction",
                    ageGroups = checkBoxes.filter { findViewById<CheckBox>(it.first).isChecked }
                        .map { it.second }
                )

                if (editingBookId != null) {
                    val index = bookList.indexOfFirst { it.id == editingBookId }
                    if (index != -1) bookList[index] = updatedBook
                } else {
                    bookList.add(updatedBook)
                }
                Intent(this, BookListActivity::class.java).also {
                    startActivity(it)
                }
                resetFields(bookName, authorName, selectedDate, genreSpinner, checkBoxes, rbFiction, rbNonFiction)
            }
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
                selectDate.setText(formattedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )
        datePickerDialog.show()
    }
}
