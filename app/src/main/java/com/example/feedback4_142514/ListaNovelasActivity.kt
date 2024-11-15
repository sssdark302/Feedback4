// ListaNovelasActivity.kt
package com.example.feedback4_142514

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4_142514.modelos.Novela

class ListaNovelasActivity : AppCompatActivity(), FragmentListaNovelas.OnNovelaClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_novelas)

        // Configura el botón para abrir NovelaManagerActivity
        val buttonToManagerActivity = findViewById<Button>(R.id.buttonToManagerActivity)
        buttonToManagerActivity.setOnClickListener {
            val intent = Intent(this, NovelaManagerActivity::class.java)
            startActivity(intent)
        }

        // Cargar FragmentListaNovelas como pantalla inicial
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentListaNovelas())
                .commit()
        }
    }

    // Implementación de OnNovelaClickListener para manejar la selección de una novela
    override fun onNovelaClick(novela: Novela) {
        val detallesFragment = FragmentDetallesNovelas().apply {
            configurar(novela)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detallesFragment)
            .addToBackStack(null)
            .commit()
    }
}
