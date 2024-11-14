package com.example.feedback4_142514

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.feedback4_142514.modelos.Novela

class NovelaWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val novela = getFavoriteNovela()

        val views = RemoteViews(context.packageName, R.layout.novela_widget)
        views.setTextViewText(R.id.tituloTextView, novela?.titulo ?: "Sin favorita")
        views.setTextViewText(R.id.autorTextView, "Autor: ${novela?.autor ?: "Desconocido"}")

        // Intent para abrir DetallesNovelaActivity con el id de la novela favorita
        val detallesIntent = Intent(context, DetallesNovelaActivity::class.java).apply {
            putExtra("novelaId", novela?.getId())
        }
        val detallesPendingIntent = PendingIntent.getActivity(
            context,
            0,
            detallesIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.verDetallesButton, detallesPendingIntent)

        // Intent para abrir ListaNovelasActivity (lista completa de novelas)
        val listaIntent = Intent(context, ListaNovelasActivity::class.java)
        val listaPendingIntent = PendingIntent.getActivity(
            context,
            0,
            listaIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.verListaButton, listaPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    // Método para obtener la novela favorita (puede ser desde la base de datos o un valor de ejemplo)
    private fun getFavoriteNovela(): Novela {
        return Novela(
            id = 1,  // Id de ejemplo
            titulo = "Título Favorito",
            autor = "Autor Favorito",
            genero = "Género Favorito",
            paginas = 300,
            descripcion = "Descripción Favorita",
            esFavorita = true
        )
    }
}
