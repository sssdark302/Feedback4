package com.example.feedback4_142514

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class NovelaDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "biblioteca.db"
        const val DATABASE_VERSION = 2 // Incrementa la versión si necesitas actualizar la base de datos

        const val TABLE_NOVELAS = "novelas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_AUTOR = "autor"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_PAGINAS = "paginas"
        const val COLUMN_DESCRIPCION = "descripcion"
        const val COLUMN_ES_FAVORITA = "esFavorita" // Nueva columna para estado de favorito

        private const val SQL_CREATE_ENTRIES = """
            CREATE TABLE $TABLE_NOVELAS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT NOT NULL,
                $COLUMN_AUTOR TEXT NOT NULL,
                $COLUMN_GENERO TEXT,
                $COLUMN_PAGINAS INTEGER,
                $COLUMN_DESCRIPCION TEXT,
                $COLUMN_ES_FAVORITA INTEGER DEFAULT 0
            )
        """

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NOVELAS"
    }
}
