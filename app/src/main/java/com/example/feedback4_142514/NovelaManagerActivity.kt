package com.example.feedback4_142514

import NovelaAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

class NovelaManagerActivity : AppCompatActivity() {

    private lateinit var novelaManager: NovelaManager
    private lateinit var novelaAdapter: NovelaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novela_manager)

        novelaManager = NovelaManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNovelas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        novelaAdapter = NovelaAdapter(novelaManager.getAllNovelas(), this, novelaManager)
        recyclerView.adapter = novelaAdapter

        val editTextNovelaTitle = findViewById<EditText>(R.id.editTextNovelaTitle)
        val editTextNovelaAutor = findViewById<EditText>(R.id.editTextNovelaAutor)
        val editTextNovelaGenero = findViewById<EditText>(R.id.editTextNovelaGenero)
        val editTextNovelaPaginas = findViewById<EditText>(R.id.editTextNovelaPaginas)
        val editTextNovelaDescripcion = findViewById<EditText>(R.id.editTextNovelaDescripcion)

        val buttonAddNovela = findViewById<Button>(R.id.buttonAddNovela)
        buttonAddNovela.setOnClickListener {
            val title = editTextNovelaTitle.text.toString()
            val autor = editTextNovelaAutor.text.toString()
            val genero = editTextNovelaGenero.text.toString()
            val paginas = editTextNovelaPaginas.text.toString().toIntOrNull() ?: 0
            val descripcion = editTextNovelaDescripcion.text.toString()

            if (title.isNotEmpty() && autor.isNotEmpty()) {
                val nuevaNovela = Novela(
                    titulo = title,
                    autor = autor,
                    genero = genero,
                    paginas = paginas,
                    descripcion = descripcion
                )
                novelaManager.addNovela(nuevaNovela)
                novelaAdapter.updateNovelas(novelaManager.getAllNovelas())

                editTextNovelaTitle.text.clear()
                editTextNovelaAutor.text.clear()
                editTextNovelaGenero.text.clear()
                editTextNovelaPaginas.text.clear()
                editTextNovelaDescripcion.text.clear()
            }
        }

        val buttonViewFavorites = findViewById<Button>(R.id.buttonViewFavorites)
        buttonViewFavorites.setOnClickListener {
            val intent = Intent(this, FavoriteNovelasActivity::class.java)
            startActivity(intent)
        }
    }
}
