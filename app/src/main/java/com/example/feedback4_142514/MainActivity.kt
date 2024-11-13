package com.example.feedback4_142514

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.feedback4_142514.ui.theme.Feedback4_142514Theme
import com.example.feedback4_142514.R
import java.util.Calendar

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        val buttonStart = findViewById<Button>(R.id.buttonStart)

        // Cambiar el saludo según la hora del día
        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            currentTime in 0..11 -> "Buenos días"
            currentTime in 12..17 -> "Buenas tardes"
            else -> "Buenas noches"
        }
        greetingTextView.text = greeting

        //Navegar a la principal
        buttonStart.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Feedback4_142514Theme {
            ("Android")
        }
    }
}