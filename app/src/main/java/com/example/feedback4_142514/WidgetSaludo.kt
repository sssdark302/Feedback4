package com.example.feedback4_142514

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.feedback4_142514.ui.theme.Feedback4_142514Theme
import java.util.Calendar

class WidgetSaludo : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        for (AppWidgetId in appWidgetIds!!) {
            val views = RemoteViews(context!!.packageName, R.layout.appwidget)


            val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val greeting = when {
                currentTime in 0..11 -> "Buenos días"
                currentTime in 12..17 -> "Buenas tardes"
                else -> "Buenas noches"
            }
            views.setTextViewText(R.id.greetingTextView, greeting)

            val intent = Intent(context, PaginaPrincipal::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.buttonStart, pendingIntent)

            appWidgetManager!!.updateAppWidget(AppWidgetId, views)

        }
    }
}
@Preview(showBackground = true)
@Composable
fun WidgetSaludoPreview() {
    Feedback4_142514Theme {
        ("Android")
    }
}