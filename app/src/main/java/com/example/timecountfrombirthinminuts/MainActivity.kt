 package com.example.timecountfrombirthinminuts

import android.app.DatePickerDialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.example.timecountfrombirthinminuts.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.Typography.times

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         binding.buttonDatePicker.setOnClickListener {view->

             Toast.makeText(this,R.string.toast_message, Toast.LENGTH_SHORT).show()

             clickDatePicker(view)
         }
     }
     private fun clickDatePicker(view: View) {
         val myCalendar = Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

         val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {

                 _, selectedYear, selectedMonth, selectedDayOfMonth ->

                 Toast.makeText(this,"The chosen year is $selectedYear month is " +
                         "${selectedMonth + 1} and day of month $selectedDayOfMonth"
                         ,Toast.LENGTH_SHORT).show()

                 val selectedDate = " $dayOfMonth/${selectedMonth + 1}/$selectedYear"

                 binding.tvSelectedDate.text = selectedDate

                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                 val theDate = sdf.parse(selectedDate)

                 val selectedDateInMinutes = theDate!!.time / 60000
                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 val currentDateInMinutes = currentDate!!.time / 60000
                 val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                 binding.tvSelectedDateInMinute.text = differenceInMinutes.toString()

             }, year, month, dayOfMonth)
         dpd.datePicker.maxDate = Date().time - 86400000
//         dpd.datePicker.setBackgroundColor(R.color.new_color_back_ground_color)
         dpd.show()
     }
     }