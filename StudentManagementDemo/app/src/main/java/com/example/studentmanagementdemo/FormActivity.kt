package com.example.studentmanagementdemo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class FormActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private var studentId: Int? = null
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        db = AppDatabase.getInstance(this)

        val nameField = findViewById<EditText>(R.id.edtName)
        val emailField = findViewById<EditText>(R.id.edtEmail)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbSports = findViewById<CheckBox>(R.id.cbSports)
        val cbMusic = findViewById<CheckBox>(R.id.cbMusic)
        spinner = findViewById(R.id.spinnerDept)

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("CS", "IT", "Mech", "Civil"))

        studentId = intent.getIntExtra("studentId", -1).takeIf { it != -1 }

        studentId?.let { id ->
            lifecycleScope.launch {
                val student = db.studentDao().getById(id)
                nameField.setText(student.name)
                emailField.setText(student.email)
                rgGender.check(if (student.gender == "Male") R.id.rbMale else R.id.rbFemale)
                spinner.setSelection((spinner.adapter as ArrayAdapter<String>).getPosition(student.department))
                cbSports.isChecked = "Sports" in student.hobbies
                cbMusic.isChecked = "Music" in student.hobbies
            }
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val name = nameField.text.toString()
            val email = emailField.text.toString()
            val gender = if (rgGender.checkedRadioButtonId == R.id.rbMale) "Male" else "Female"
            val department = spinner.selectedItem.toString()
            val hobbies = listOfNotNull(
                cbSports.takeIf { it.isChecked }?.text,
                cbMusic.takeIf { it.isChecked }?.text
            ).joinToString(",")

            lifecycleScope.launch {
                val student = Student(studentId ?: 0, name, email, gender, department, hobbies)
                if (studentId == null) db.studentDao().insert(student)
                else db.studentDao().update(student)
                finish()
            }
        }
    }
}