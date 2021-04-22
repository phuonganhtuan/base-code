package com.example.demomotion.widget

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import androidx.core.content.ContextCompat
import com.example.demomotion.R
import com.example.demomotion.data.database.AppDatabase
import com.example.demomotion.data.entity.DemoEntity
import java.text.SimpleDateFormat
import java.util.*


class WidgetRemoteFactory(private val context: Context, intent: Intent?) :
    RemoteViewsFactory {

    private val taskDao by lazy { AppDatabase.invoke(context).demoDao() }
    private var todayTasks = listOf<DemoEntity>()

    override fun onCreate() {
    }

    override fun onDataSetChanged() {
        todayTasks = taskDao.getAllEntities().value ?: return
    }

    override fun onDestroy() {
    }

    override fun getCount() = todayTasks.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.item_task_widget)

        // Setup views
        return rv
    }

    override fun getLoadingView(): RemoteViews {
        return RemoteViews(context.packageName, R.layout.item_task_widget)
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int) = todayTasks[position].id.toLong()

    override fun hasStableIds() = true
}
