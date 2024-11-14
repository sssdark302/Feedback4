package com.example.feedback4_142514

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import android.appwidget.AppWidgetManager
import android.content.ComponentName

class PaginaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        val updateButton = findViewById<Button>(R.id.updateButton)

        // Configuración del texto inicial
        greetingTextView.text = "Bienvenido a tu Biblioteca de Novelas"

        // Configuración del botón para actualizar el widget
        updateButton.setOnClickListener {
            actualizarWidget()
        }
    }

    private fun actualizarWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this, NovelaWidgetProvider::class.java))

        // Crear Intent con la acción de actualizar widgets
        val intent = Intent(this, NovelaWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        }

        // Enviar broadcast para actualizar los widgets
        sendBroadcast(intent)
    }

}
