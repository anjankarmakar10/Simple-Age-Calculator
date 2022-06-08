package com.example.android.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.android.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDatePicker.setOnClickListener {
            clickDatePicker(it)
        }

    }

    private fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()

        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->

                val selectedDate = "$day/${month + 1}/$year"

                binding.tvDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDateInMinutes = currentDate!!.time / 60000

                val dateInMinutes = currentDateInMinutes - selectedDateInMinutes

                val month = dateInMinutes * 2.2816E-5
                val days = dateInMinutes / 1440
                val hour = dateInMinutes / 60

                binding.tvMonths.text = "%.0f".format(month)
                binding.tvDays.text = days.toString()
                binding.tvHours.text = hour.toString()

            }, year, month, day
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()


    }
}