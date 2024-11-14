package com.example.feedback4_142514

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4_142514.modelos.Novela

class ListaNovelasActivity : AppCompatActivity(), FragmentListaNovelas.OnNovelaClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_novelas)

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
