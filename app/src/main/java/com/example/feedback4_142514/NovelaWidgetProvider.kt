package com.example.feedback4_142514

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

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
        val novela = getFavoriteNovela(context)

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

        val listaIntent = Intent(context, ListaNovelasActivity::class.java)
        val listaPendingIntent = PendingIntent.getActivity(
            context, 0, listaIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.verListaButton, listaPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getFavoriteNovela(context: Context): Novela? {
        val novelaManager = NovelaManager(context)
        return novelaManager.getLastFavoriteNovela()
    }
}
