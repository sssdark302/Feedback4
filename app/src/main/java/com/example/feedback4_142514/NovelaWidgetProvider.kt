package com.example.feedback4_142514

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class NovelaWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            // Crear intent para abrir la app cuando se hace clic en el widget
            val intent = Intent(context, PaginaPrincipal::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )

            // Configurar RemoteViews para el layout del widget
            val views = RemoteViews(context.packageName, R.layout.novela_widget)
            views.setOnClickPendingIntent(R.id.widgetContainer, pendingIntent)

            // Configuración inicial del widget (por ejemplo, puedes cargar un título de ejemplo)
            views.setTextViewText(R.id.widgetTitle, "Bienvenido a tu Biblioteca")
            views.setTextViewText(R.id.widgetNovelaTitle, "Ejemplo de novela")

            // Actualizar el widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
