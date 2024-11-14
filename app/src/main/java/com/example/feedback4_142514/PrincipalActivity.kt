package com.example.feedback4_142514

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PrincipalActivity : AppCompatActivity() {

    private lateinit var dbHelper: NovelaDatabaseHelper
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var progressBar: ProgressBar
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        dbHelper = NovelaDatabaseHelper(this)
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val displayTextView = findViewById<TextView>(R.id.displayTextView)
        val saveToDbButton = findViewById<Button>(R.id.saveToDbButton)
        val loadFromDbButton = findViewById<Button>(R.id.loadFromDbButton)
        val clearDbButton = findViewById<Button>(R.id.clearDbButton)
        val goToConfigButton = findViewById<Button>(R.id.goToConfigButton)
        val btnStartTask = findViewById<Button>(R.id.btnStartTask)
        val btnGoToMenu = findViewById<Button>(R.id.btnGoToMenu)
        progressBar = findViewById(R.id.progressBar)

        saveButton.setOnClickListener {
            val userName = nameEditText.text.toString()
            saveUserNameToPreferences(userName)
            displayTextView.text = userName
        }

        saveToDbButton.setOnClickListener {
            val userName = nameEditText.text.toString()
            saveUserToDatabase(userName)
        }

        loadFromDbButton.setOnClickListener {
            displayTextView.text = getUserNames().joinToString(", ")
        }

        clearDbButton.setOnClickListener {
            clearDatabase()
            displayTextView.text = ""
        }

        goToConfigButton.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        btnStartTask.setOnClickListener {
            simulateNetworkOperation()
        }

        btnGoToMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        displayTextView.text = getUserNameFromPreferences()
    }

    private fun simulateNetworkOperation() {
        progressBar.visibility = ProgressBar.VISIBLE
        progressBar.progress = 0

        val totalProgressTime = 2000 // 2 seconds
        val interval = 100 // 100 milliseconds

        for (i in 0..totalProgressTime step interval) {
            handler.postDelayed({
                progressBar.progress = (i * 100 / totalProgressTime)
                if (i >= totalProgressTime) {
                    progressBar.visibility = ProgressBar.GONE
                }
            }, i.toLong())
        }
    }

    private fun saveUserNameToPreferences(name: String) {
        with(sharedPreferences.edit()) {
            putString("user_name", name)
            apply()
        }
    }

    private fun getUserNameFromPreferences(): String? {
        return sharedPreferences.getString("user_name", "No name saved")
    }

    private fun saveUserToDatabase(name: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        val newRowId = db.insert("users", null, values)
        Log.d("PrincipalActivity", "New user inserted with ID: $newRowId")
    }

    private fun getUserNames(): List<String> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("users", arrayOf("name"), null, null, null, null, null)
        val userNames = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow("name"))
                userNames.add(name)
            }
        }
        cursor.close()
        Log.d("PrincipalActivity", "Retrieved users: $userNames")
        return userNames
    }

    private fun clearDatabase() {
        val db = dbHelper.writableDatabase
        db.delete("users", null, null)
        Log.d("PrincipalActivity", "Database cleared")
    }
}