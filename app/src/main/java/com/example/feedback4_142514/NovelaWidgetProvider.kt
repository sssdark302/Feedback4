import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.feedback4_142514.NovelaDatabaseHelper
import com.example.feedback4_142514.PaginaPrincipal
import com.example.feedback4_142514.R

class NovelaWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        if (context == null || appWidgetIds == null || appWidgetManager == null) return

        val dbHelper = NovelaDatabaseHelper(context)
        val novelas = dbHelper.getAllNovelas()
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.novela_widget)

            // Mostrar el título de la primera novela (como ejemplo)
            if (novelas.isNotEmpty()) {
                views.setTextViewText(R.id.novelaTitle, novelas[0].titulo)
                views.setTextViewText(R.id.novelaAutor, novelas[0].autor)
            } else {
                views.setTextViewText(R.id.novelaTitle, "No hay novelas")
                views.setTextViewText(R.id.novelaAutor, "")
            }

            // Crear Intent para abrir la aplicación al tocar el widget
            val intent = Intent(context, PaginaPrincipal::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widgetLayout, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
