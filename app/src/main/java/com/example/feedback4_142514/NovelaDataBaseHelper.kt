package com.example.feedback4_142514

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class NovelaDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "biblioteca.db"
        const val DATABASE_VERSION = 1

        // Nombre de la tabla y columnas
        const val TABLE_NOVELAS = "novelas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_AUTOR = "autor"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_PAGINAS = "paginas"
        const val COLUMN_DESCRIPCION = "descripcion"

        // Creación de la tabla
        private const val SQL_CREATE_ENTRIES = """
            CREATE TABLE $TABLE_NOVELAS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT NOT NULL,
                $COLUMN_AUTOR TEXT NOT NULL,
                $COLUMN_GENERO TEXT,
                $COLUMN_PAGINAS INTEGER,
                $COLUMN_DESCRIPCION TEXT
            )
        """

        // Eliminación de la tabla
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NOVELAS"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // Función para agregar una novela a la base de datos
    fun addNovela(novela: Novela): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, novela.titulo)
            put(COLUMN_AUTOR, novela.autor)
            put(COLUMN_GENERO, novela.genero)
            put(COLUMN_PAGINAS, novela.paginas)
            put(COLUMN_DESCRIPCION, novela.descripcion)
        }
        return db.insert(TABLE_NOVELAS, null, values)
    }

    fun getAllNovelas(): List<Novela> {
        val novelas = mutableListOf<Novela>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NOVELAS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
                val autor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTOR))
                val genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO))
                val paginas = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PAGINAS))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                novelas.add(Novela(id, titulo, autor, genero, paginas, descripcion))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return novelas
    }


    // Función para actualizar una novela
    fun updateNovela(novela: Novela): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, novela.titulo)
            put(COLUMN_AUTOR, novela.autor)
            put(COLUMN_GENERO, novela.genero)
            put(COLUMN_PAGINAS, novela.paginas)
            put(COLUMN_DESCRIPCION, novela.descripcion)
        }
        return db.update(TABLE_NOVELAS, values, "$COLUMN_ID = ?", arrayOf(novela.id.toString()))
    }

    // Función para eliminar una novela
    fun deleteNovela(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NOVELAS, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}
