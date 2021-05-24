package com.Kate.habito.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.widget.TimePicker
import com.Kate.habito.R
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var mOnTimeSetListener: OnTimeSetListener? = null
    private var mHour: Int = 0
    private var mMinutes: Int = 0


    interface OnTimeSetListener {


        fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int)

        fun onCancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            this.mHour = args.getInt(HOUR_EXTRA_KEY)
            this.mMinutes = args.getInt(MINUTES_EXTRA_KEY)
        } else {
            // Use the current time as the default values for the time picker.
            val calendar = Calendar.getInstance()
            mHour = calendar.get(Calendar.HOUR_OF_DAY)
            mMinutes = calendar.get(Calendar.MINUTE)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = TimePickerDialog(activity, this, mHour, mMinutes, DateFormat.is24HourFormat(activity))
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.off)) { _, _ ->
            mOnTimeSetListener?.onCancel()
        }

        return dialog
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        mOnTimeSetListener?.onTimeSet(view, hourOfDay, minute)
    }

    fun setOnTimeSetListener(onTimeSetListener: OnTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener
    }

    companion object {

        val HOUR_EXTRA_KEY = "hour"
        val MINUTES_EXTRA_KEY = "minutes"

        fun newInstance(hour: Int, minutes: Int): TimePickerFragment {
            val timePickerFragment = TimePickerFragment()

            val args = Bundle()
            args.putInt(HOUR_EXTRA_KEY, hour)
            args.putInt(MINUTES_EXTRA_KEY, minutes)
            timePickerFragment.arguments = args

            return timePickerFragment
        }
    }
}
