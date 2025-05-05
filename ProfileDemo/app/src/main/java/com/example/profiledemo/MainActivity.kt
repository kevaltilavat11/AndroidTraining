package com.example.profiledemo
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var etDob: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDob = findViewById(R.id.etDob)
        val calendar = Calendar.getInstance()

        etDob.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    val dob = "$day/${month + 1}/$year"
                    etDob.setText(dob)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }



        findViewById<MaterialButton>(R.id.btnSubmit).setOnClickListener {
            val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
            val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
            val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
            val etCountry = findViewById<TextInputEditText>(R.id.etCountry)
            val etAddress = findViewById<TextInputEditText>(R.id.etAddress)
            val etDob = findViewById<TextInputEditText>(R.id.etDob)

            val name = etFullName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val country = etCountry.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val dob = etDob.text.toString().trim()

            var isValid = true

            if (name.isEmpty()) {
                etFullName.error = getString(R.string.require_fullName)
                isValid = false
            }

            if (phone.isEmpty()) {
                etPhone.error = getString(R.string.require_pNumber)
                isValid = false
            }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = getString(R.string.require_email)
                isValid = false
            }

            if (country.isEmpty()) {
                etCountry.error = getString(R.string.require_country)
                isValid = false
            }

            if (address.isEmpty()) {
                etAddress.error = getString(R.string.require_address)
                isValid = false
            }

            if (dob.isEmpty()) {
                etDob.error = getString(R.string.require_dob)
                isValid = false
            }

            val genderId = findViewById<RadioGroup>(R.id.rgGender).checkedRadioButtonId
            if (genderId == -1) {
                Toast.makeText(this, R.string.select_gender, Toast.LENGTH_SHORT).show()
                isValid = false
            }

            val hobbies = mutableListOf<String>()
            if (findViewById<CheckBox>(R.id.cbReading).isChecked) hobbies.add("Reading")
            if (findViewById<CheckBox>(R.id.cbTraveling).isChecked) hobbies.add("Traveling")
            if (findViewById<CheckBox>(R.id.cbGaming).isChecked) hobbies.add("Gaming")

            if (hobbies.isEmpty()) {
                Toast.makeText(this, R.string.select_hobby, Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {
                val intent = Intent(this, EducationActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Profile Submitted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}