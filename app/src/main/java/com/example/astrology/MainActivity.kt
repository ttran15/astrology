package com.example.astrology

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btCheck: Button = findViewById(R.id.btCheck)
        val spMonth : Spinner = findViewById(R.id.spMonth)
        val spDate : Spinner = findViewById(R.id.spDate)
        val ls_month = arrayOf("January","February","March","April","May","June","July","August","September","October","November","December")
        var selectedMonth: String = ""
        var selectedDate = 0

        spMonth.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ls_month)
        spMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedMonth = ls_month[position]
                updateDateSpinner(selectedMonth)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        spDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedDate = position + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        btCheck.setOnClickListener {
            checkZodiacSigns(selectedMonth,selectedDate)
        }

    }

    // set number of dates based on month
    private fun updateDateSpinner(selectedMonth: String) {
        val spDate : Spinner = findViewById(R.id.spDate)
        val ls_date_31 = Array(31) { (it + 1).toString() }
        val ls_date_30 = Array(30) { (it + 1).toString() }
        val ls_date_29 = Array(29) { (it + 1).toString() }
        val ls_date = when (selectedMonth) {
            "January", "March", "May", "July", "August", "October", "December" -> ls_date_31
            "April", "June", "September", "November" -> ls_date_30
            "February" -> ls_date_29
            else -> ls_date_31
        }
        spDate.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,ls_date)
    }

    private fun checkZodiacSigns(selectedMonth: String,selectedDate: Int) {
        val txtResult: TextView = findViewById(R.id.txtResult)
        val txtDescription: TextView = findViewById(R.id.txtDescription)
        val result = when (selectedMonth) {
            "January" -> if (selectedDate <= 19) "Capricorn" else "Aquarius"
            "February" -> if (selectedDate <= 18) "Aquarius" else "Pisces"
            "March" -> if (selectedDate <= 20) "Pisces" else "Aries"
            "April" -> if (selectedDate <= 19) "Aries" else "Taurus"
            "May" -> if (selectedDate <= 20) "Taurus" else "Gemini"
            "June" -> if (selectedDate <= 21) "Gemini" else "Cancer"
            "July" -> if (selectedDate <= 22) "Cancer" else "Leo"
            "August" -> if (selectedDate <= 22) "Leo" else "Virgo"
            "September" -> if (selectedDate <= 22) "Virgo" else "Libra"
            "October" -> if (selectedDate <= 22) "Libra" else "Scorpio"
            "November" -> if (selectedDate <= 21) "Scorpio" else "Sagittarius"
            "December" -> if (selectedDate <= 21) "Sagittarius" else "Capricorn"
            else -> ""
        }
        // txtResult.text = selectedMonth + " " + selectedDate.toString()
        txtResult.text = result

        val description = when (result){
            "Capricorn" -> "\naspiring, responsible, and focused. \nCapricorns are diligent workers who are renowned for devotion and dependability."
            "Aquarius" -> "\ncreative, self-reliant, and altruistic. \nAquarians are intellectuals who respect individualism and social causes."
            "Pisces" -> "\nartistic, perceptive, and compassionate. \nPisces people  are inventive, sensitive, and have a great empathy for other people."
            "Aries" -> "\nbrave, energetic, and daring. \nAries are bold, spontaneous, and recognized for their love of challenges."
            "Taurus" -> "\ntrustworthy, caring, and persistent. \nTaurus are stable, practical, and frequently renowned for having strong resolve."
            "Gemini" -> "\nadaptable, inquisitive, and outgoing. \nGeminis are clever, love to communicate, and enjoy knowledge."
            "Cancer" -> "\nprotective, sympathetic, and nurturing. \nCancers value their family, and frequently use art as a means of self-expression."
            "Leo" -> "\nattractive, generous, and self-assured. \nLeos have an intense love of self-expression and are innate leaders."
            "Virgo" -> "\nanalytical, industrious, and practical. \nVirgos are logic-loving perfectionists who are frequently committed to serving others."
            "Libra" -> "\nadorable, courteous, and impartial. \nLibras value social relationships, and are seekers of harmony and balance."
            "Scorpio" -> "\ndetermined, enthusiastic, and brilliant. \nScorpios have deep emotional and strong feeling of commitment."
            "Sagittarius" -> "\nindependent, bold, and full of optimism. \nSagittarius individuals are people who also enjoy adventure and freedom."
            else -> ""
        }

        txtDescription.text = description
    }
}