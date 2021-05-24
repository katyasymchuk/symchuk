package com.Kate.habito.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Kate.habito.R
import com.Kate.habito.model.Habit
import com.Kate.habito.model.HabitList
import com.Kate.habito.view.model.HabitListItemViewModel


class HabitsAdapter(private val habitList: HabitList,
                    var clickListener: OnClickListener? = null)
    : RecyclerView.Adapter<HabitsAdapter.HabitAdapterViewHolder>() {

    var habits: MutableList<Habit>?
        get() = habitList.habits
        set(newValue) {
            if (newValue == null) {
                habitList.clear()
            } else {
                habitList.habits = newValue
            }
            notifyDataSetChanged()
        }


    interface OnClickListener {

        fun onClick(habit: Habit, position: Int)
    }

    fun setSortOrder(sortOrder: HabitList.SortOrder) {
        habitList.sortOrder = sortOrder
        notifyDataSetChanged()
    }

    fun clear() {
        habitList.clear()
        notifyDataSetChanged()
    }

    fun add(habit: Habit) {
        habitList.add(habit)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listView = layoutInflater.inflate(R.layout.habit_list_item, parent, false)
        listView.isFocusable = true

        return HabitAdapterViewHolder(listView)
    }

    override fun onBindViewHolder(viewHolder: HabitAdapterViewHolder, position: Int) {
        viewHolder.bindAtPosition(position)
    }

    override fun getItemCount(): Int {
        return habitList.habits?.size ?: 0
    }

    private fun getHabit(index: Int): Habit {
        return habitList.habits!![index]
    }


    inner class HabitAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val viewModel: HabitListItemViewModel

        init {
            view.setOnClickListener(this)
            viewModel = HabitListItemViewModel(view.context)
        }

        override fun onClick(view: View) {
            clickListener?.onClick(getHabit(adapterPosition), adapterPosition)
        }

        fun bindAtPosition(position: Int) {
            viewModel.habit = getHabit(position)

            if (view is CardView) {
                view.setCardBackgroundColor(viewModel.backgroundColor)
            } else {
                view.setBackgroundColor(viewModel.backgroundColor)
            }


        }
    }
}
