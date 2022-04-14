package org.solutionstree.agecalculator
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var tvAgeInMinute:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinute=findViewById(R.id.tvAgeInMinute)
        val btnDatePicker : Button=findViewById(R.id.btnDatePicker)
         btnDatePicker.setOnClickListener {
             clickDatePicker()
         }
    }
    private fun clickDatePicker(){
    val myCalander=Calendar.getInstance()
        val year=myCalander.get(Calendar.YEAR)
        val month=myCalander.get(Calendar.MONTH)
        val day=myCalander.get(Calendar.DAY_OF_MONTH)
       val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {view,selectedyear,selectedmonth,selectedDayOfMonth ->
                val selectedDate="$selectedDayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelectedDate?.setText(selectedDate)
                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                theDate?.let {
                    val seletedDateInMinute = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinute = currentDate.time / 60000
                        val diffrentInMinute = currentDateInMinute - seletedDateInMinute
                        tvAgeInMinute?.text = diffrentInMinute.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}