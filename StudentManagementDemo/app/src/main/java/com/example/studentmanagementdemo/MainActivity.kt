package com.example.studentmanagementdemo


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)

        adapter = StudentAdapter(
            onUpdate = { student ->
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra("studentId", student.id)
                startActivity(intent)
            },
            onDelete = { student ->
                AlertDialog.Builder(this).apply {
                    setTitle("Delete Student")
                    setMessage("Are you sure you want to delete ${student.name}?")
                    setPositiveButton("Yes") { _, _ ->
                        lifecycleScope.launch {
                            db.studentDao().delete(student)
                            loadStudents()
                        }
                    }
                    setNegativeButton("No", null)
                    show()
                }
            }
        )

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudents()
    }

    private fun loadStudents() {
        lifecycleScope.launch {
            val students = db.studentDao().getAll()
            adapter.submitList(students)
        }
    }
}