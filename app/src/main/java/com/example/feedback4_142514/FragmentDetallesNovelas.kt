package com.example.feedback4_142514

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentDetallesNovelas : Fragment() {
    private lateinit var dbHelper: NovelaDatabaseHelper
    private var novela: Novela? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalles_novela, container, false)
        dbHelper = NovelaDatabaseHelper(requireContext())

        val tituloEditText = view.findViewById<EditText>(R.id.tituloEditText)
        val autorEditText = view.findViewById<EditText>(R.id.autorEditText)
        val generoEditText = view.findViewById<EditText>(R.id.generoEditText)
        val paginasEditText = view.findViewById<EditText>(R.id.paginasEditText)
        val descripcionEditText = view.findViewById<EditText>(R.id.descripcionEditText)

        view.findViewById<Button>(R.id.guardarNovelaButton).setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val autor = autorEditText.text.toString()
            val genero = generoEditText.text.toString()
            val paginas = paginasEditText.text.toString().toInt()
            val descripcion = descripcionEditText.text.toString()

            if (novela == null) {
                val nuevaNovela = Novela(0, titulo, autor, genero, paginas, descripcion)
                dbHelper.addNovela(nuevaNovela)
            } else {
                novela?.titulo = titulo
                dbHelper.updateNovela(novela!!)
            }

            // Regresar a la lista de novelas
            (activity as? PaginaPrincipal)?.showListFragment()
        }

        return view
    }
}