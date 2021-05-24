package com.Kate.habito.barchart

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

import com.Kate.habito.model.Habit

class HabitoBarChartDataLoader(context: Context,
                               private val habit: Habit,
                               private val dateRange: HabitoBarChartRange.DateRange)
    : AsyncTaskLoader<HabitoBarChartDataSource>(context) {

    override fun onStartLoading() {
        forceLoad()
    }

    override fun loadInBackground(): HabitoBarChartDataSource {
        val dataSource = HabitoBarChartDataSource(habit, dateRange)
        dataSource.prefetch()

        return dataSource
    }
}
