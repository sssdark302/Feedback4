package com.example.feedback4_142514

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

class MainActivity : AppCompatActivity(), FragmentListaNovelas.OnNovelaClickListener, FragmentDetallesNovelas.OnDetalleNovelaListener {

    private lateinit var novelaManager: NovelaManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        novelaManager = NovelaManager(this)

        // Cargar el FragmentListaNovelas como pantalla inicial
        if (savedInstanceState == null) {
            replaceFragment(FragmentListaNovelas())
        }
    }

    // Implementación de la interfaz OnNovelaClickListener de FragmentListaNovelas
    override fun onNovelaClick(novela: Novela) {
        val detallesFragment = FragmentDetallesNovelas().apply {
            configurar(novela)
        }
        replaceFragment(detallesFragment)
    }

    // Implementación de la interfaz OnDetalleNovelaListener de FragmentDetallesNovelas
    override fun onMarcarFavorita(novela: Novela) {
        novelaManager.marcarComoFavorita(novela.getId()!!, novela.esFavorita)
    }

    override fun onVolverALaLista() {
        replaceFragment(FragmentListaNovelas())
    }

    // Método para reemplazar el fragment actual
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
