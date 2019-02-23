package com.sportpedia.util

import android.content.Context
import android.view.View
import android.widget.EditText
import com.takisoft.datetimepicker.DatePickerDialog
import com.takisoft.datetimepicker.TimePickerDialog
import java.text.SimpleDateFormat
import java.util.*

object Dialogs {

    fun setDate(context: Context, view: View) {
        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val cl = Calendar.getInstance()
                cl.set(year, month, day)
                val date = cl.time
                val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                (view as EditText).setText(sdf.format(date))
            },
            currentYear, currentMonth, currentDay
        )
        datePickerDialog.show()
    }

    fun toBookedId(dateString: String): String {
        val sdfIn = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        val cl = Calendar.getInstance().apply {
            time = sdfIn.parse(dateString)
        }

        val y = cl.get(Calendar.YEAR)
        val m = cl.get(Calendar.MONTH) + 1
        val d = cl.get(Calendar.DAY_OF_MONTH)

        return "$y$m$d"
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