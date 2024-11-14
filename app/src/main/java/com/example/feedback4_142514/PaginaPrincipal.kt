package com.example.feedback4_142514

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PaginaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)
        showListFragment()
    }

    fun showListFragment() {
        val fragment = FragmentListaNovelas()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun showDetailsFragment(novela: Novela? = null) {
        val fragment = FragmentDetallesNovelas()
        if (novela != null) {
            val bundle = Bundle()
            bundle.putParcelable("novela", novela)
            fragment.arguments = bundle
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}