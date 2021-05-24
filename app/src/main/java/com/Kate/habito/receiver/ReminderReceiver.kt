package com.Kate.habito.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.Kate.habito.model.Habit
import com.Kate.habito.sync.ReminderIntentService
import com.Kate.habito.util.ReminderUtils

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.hasExtra(ReminderUtils.HABIT_EXTRA_KEY)) {
            val habit = intent.getParcelableExtra<Habit>(ReminderUtils.HABIT_EXTRA_KEY)
            val serviceIntent = ReminderIntentService.buildInstance(habit, context)
            context.startService(serviceIntent)
        }
    }
}
