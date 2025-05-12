package com.example.studentdemo

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var colorSpinner: Spinner
    private lateinit var folderSpinner: Spinner
    private lateinit var preferences: SharedPreferences

    private val colors = mapOf(
        "Purple" to "#6200EE",
        "Red" to "#B00020",
        "Green" to "#388E3C",
        "Blue" to "#1976D2"
    )

    private val folders = listOf(
        "Pictures/Screenshots",
        "DCIM/Camera",
        "Download",
        "WhatsApp/Media/WhatsApp Images"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        colorSpinner = findViewById(R.id.spinnerColor)
        folderSpinner = findViewById(R.id.spinnerFolder)

        colorSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, colors.keys.toList())
        folderSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, folders)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val selectedColor = colors[colorSpinner.selectedItem.toString()] ?: "#6200EE"
            val selectedFolder = folderSpinner.selectedItem.toString()

            preferences.edit().apply {
                putString("actionbar_color", selectedColor)
                putString("folder_path", selectedFolder)
                apply()
            }
            Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
