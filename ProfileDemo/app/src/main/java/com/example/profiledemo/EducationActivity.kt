package com.example.profiledemo

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.profiledemo.R

class EducationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        val spinnerSsc = findViewById<Spinner>(R.id.spinnerSsc)
        val seekBarSsc = findViewById<SeekBar>(R.id.seekBarSsc)
        val tvSscPercentage = findViewById<TextView>(R.id.tvSscPercentage)

        val spinnerBca = findViewById<Spinner>(R.id.spinnerBca)
        val seekBarBca = findViewById<SeekBar>(R.id.seekBarBca)
        val tvBcaPercentage = findViewById<TextView>(R.id.tvBcaPercentage)

        seekBarSsc.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvSscPercentage.text = "Percentage: $progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarBca.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvBcaPercentage.text = "Percentage: $progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        findViewById<Button>(R.id.btnEduSubmit).setOnClickListener {
            Toast.makeText(this, "Education details submitted", Toast.LENGTH_SHORT).show()
        }
    }
}
