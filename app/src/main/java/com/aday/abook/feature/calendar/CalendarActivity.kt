package com.aday.abook.feature.calendar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aday.abook.R
import com.aday.abook.feature.main.MainActivity
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import kotlinx.android.synthetic.main.activity_main_calendar.*

class CalendarActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calendar)

        calendarView.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(this,"$date", Toast.LENGTH_SHORT).show()
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}