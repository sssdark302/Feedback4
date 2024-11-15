// DetallesNovelaActivity.kt
package com.example.feedback4_142514

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

class DetallesNovelaActivity : AppCompatActivity(), FragmentDetallesNovelas.OnDetalleNovelaListener {

    private lateinit var novelaManager: NovelaManager
    private var novelaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_novela)

        novelaManager = NovelaManager(this)
        novelaId = intent.getIntExtra("novelaId", -1)

        // Cargar el FragmentDetallesNovelas con la novela específica
        if (savedInstanceState == null) {
            val novela = getNovelaById(novelaId)
            val detallesFragment = FragmentDetallesNovelas().apply {
                configurar(novela)
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detallesFragment)
                .commit()
        }
    }

    private fun getNovelaById(id: Int): Novela {
        return novelaManager.getAllNovelas().firstOrNull { it.getId() == id }
            ?: Novela(id = id, titulo = "No encontrado", autor = "", genero = null, paginas = 0, descripcion = "", esFavorita = false)
    }

    override fun onMarcarFavorita(novela: Novela) {
        novelaManager.marcarComoFavorita(novela.getId()!!, novela.esFavorita)
    }

    override fun onVolverALaLista() {
        finish() // Finaliza esta actividad y vuelve a la actividad de la lista
    }
}
