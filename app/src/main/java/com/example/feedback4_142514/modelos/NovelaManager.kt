package com.example.feedback4_142514.modelos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.feedback4_142514.NovelaDatabaseHelper

class NovelaManager(context: Context) {
    private val dbHelper = NovelaDatabaseHelper(context)

    fun addNovela(novela: Novela): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NovelaDatabaseHelper.COLUMN_TITULO, novela.titulo)
            put(NovelaDatabaseHelper.COLUMN_AUTOR, novela.autor)
            put(NovelaDatabaseHelper.COLUMN_GENERO, novela.genero)
            put(NovelaDatabaseHelper.COLUMN_PAGINAS, novela.paginas)
            put(NovelaDatabaseHelper.COLUMN_DESCRIPCION, novela.descripcion)
        }
        return db.insert(NovelaDatabaseHelper.TABLE_NOVELAS, null, values)
    }

    fun getAllNovelas(): List<Novela> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(NovelaDatabaseHelper.TABLE_NOVELAS, null, null, null, null, null, null)
        val novelas = mutableListOf<Novela>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_ID))
                val titulo = getString(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_TITULO))
                val autor = getString(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_AUTOR))
                val genero = getString(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_GENERO))
                val paginas = getInt(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_PAGINAS))
                val descripcion = getString(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_DESCRIPCION))
                val esFavorita = getInt(getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_ES_FAVORITA)) == 1
                novelas.add(Novela(id = id, titulo = titulo, autor = autor, genero = genero, paginas = paginas, descripcion = descripcion, esFavorita = esFavorita))
            }
        }
        cursor.close()
        return novelas
    }

    fun updateNovela(novela: Novela): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NovelaDatabaseHelper.COLUMN_TITULO, novela.titulo)
            put(NovelaDatabaseHelper.COLUMN_AUTOR, novela.autor)
            put(NovelaDatabaseHelper.COLUMN_GENERO, novela.genero)
            put(NovelaDatabaseHelper.COLUMN_PAGINAS, novela.paginas)
            put(NovelaDatabaseHelper.COLUMN_DESCRIPCION, novela.descripcion)
            put(NovelaDatabaseHelper.COLUMN_ES_FAVORITA, if (novela.esFavorita) 1 else 0)
        }
        return db.update(NovelaDatabaseHelper.TABLE_NOVELAS, values, "${NovelaDatabaseHelper.COLUMN_ID} = ?", arrayOf(novela.getId().toString()))
    }
    fun getLastFavoriteNovela(): Novela? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            NovelaDatabaseHelper.TABLE_NOVELAS,
            null,
            "${NovelaDatabaseHelper.COLUMN_ES_FAVORITA} = ?",
            arrayOf("1"),
            null,
            null,
            "${NovelaDatabaseHelper.COLUMN_ID} DESC",
            "1"
        )

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_ID))
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_TITULO))
            val autor = cursor.getString(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_AUTOR))
            val genero = cursor.getString(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_GENERO))
            val paginas = cursor.getInt(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_PAGINAS))
            val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(NovelaDatabaseHelper.COLUMN_DESCRIPCION))
            Novela(id, titulo, autor, genero, paginas, descripcion, esFavorita = true)
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    fun marcarComoFavorita(id: Int, esFavorita: Boolean): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NovelaDatabaseHelper.COLUMN_ES_FAVORITA, if (esFavorita) 1 else 0)
        }
        return db.update(NovelaDatabaseHelper.TABLE_NOVELAS, values, "${NovelaDatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
    }

    fun deleteNovela(titulo: String): Int {
        val db = dbHelper.writableDatabase
        return db.delete(NovelaDatabaseHelper.TABLE_NOVELAS, "${NovelaDatabaseHelper.COLUMN_TITULO} = ?", arrayOf(titulo))
    }

    private fun getFavoriteNovela(context: Context): Novela? {
        val novelaManager = NovelaManager(context)
        return novelaManager.getLastFavoriteNovela()
    }


}
