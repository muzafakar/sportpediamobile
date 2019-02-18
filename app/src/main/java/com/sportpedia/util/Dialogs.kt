package com.sportpedia.util

import android.content.Context
import android.view.View
import android.widget.EditText
import com.takisoft.datetimepicker.DatePickerDialog
import com.takisoft.datetimepicker.TimePickerDialog
import java.util.*

object Dialogs {

    fun setDate(context: Context, view: View) {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val date = if (day < 10) {
                    "0$day-${month + 1}-$year"
                } else {
                    "$day-${month + 1}-$year"
                }

                (view as EditText).setText(date)

            },
            currentYear, currentMonth, currentDay
        )
        datePickerDialog.show()
    }

    fun setTime(context: Context, view: View) {

        val h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val m = Calendar.getInstance().get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val time = if (minute > 30) {
                    "${hour + 1}:00"
                } else {
                    "$hour:00"
                }

                (view as EditText).setText(time)

            }, h, m, true
        )
        timePickerDialog.show()
    }
}