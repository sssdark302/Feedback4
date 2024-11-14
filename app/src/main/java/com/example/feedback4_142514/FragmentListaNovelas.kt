package com.example.feedback4_142514

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentListaNovelas : Fragment() {
    private lateinit var dbHelper: NovelaDatabaseHelper
    private lateinit var novelaAdapter: NovelaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_novela, container, false)

        // Inicializa el helper de base de datos
        dbHelper = NovelaDatabaseHelper(requireContext())

        // Obtén la lista de novelas desde la base de datos
        val novelas = dbHelper.getAllNovelas()

        // Configuración del RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNovelas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        novelaAdapter = NovelaAdapter(novelas)
        recyclerView.adapter = novelaAdapter

        return view
    }
}
