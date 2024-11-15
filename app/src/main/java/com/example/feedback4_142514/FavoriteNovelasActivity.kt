package com.example.feedback4_142514

import NovelaAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedback4_142514.modelos.NovelaManager

class FavoriteNovelasActivity : AppCompatActivity() {

    private lateinit var novelaManager: NovelaManager
    private lateinit var novelaAdapter: NovelaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_novelas)

        novelaManager = NovelaManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val favoriteNovelas = novelaManager.getAllNovelas().filter { it.esFavorita }
        novelaAdapter = NovelaAdapter(favoriteNovelas, this, novelaManager)
        recyclerView.adapter = novelaAdapter
    }
}
