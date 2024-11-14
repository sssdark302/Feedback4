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
        val recyclerView = view.findViewById<RecyclerView>(R.id.novelaRecyclerView)

        dbHelper = NovelaDatabaseHelper(requireContext())
        val novelas = dbHelper.getAllNovelas()
        novelaAdapter = NovelaAdapter(novelas) { novela ->
            (activity as? PaginaPrincipal)?.showDetailsFragment(novela)
        }

        recyclerView.adapter = novelaAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }
}