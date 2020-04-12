package com.chudy.miquidocatfacts.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chudy.miquidocatfacts.R
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * The details screen. Shows a chosen cat fact with its update date.
 */
class DetailsActivity : AppCompatActivity() {

    /**
     * When activity created, a listener is put on the back arrow to finish the activity,
     * fact text is set from extras in the intent,
     * fact date is properly formatted and set as text appropriately.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        image_arrow_back.setOnClickListener {
            finish()
        }

        val factText: String = intent.getStringExtra("factText")!!
        val factDate: String = intent.getStringExtra("factDate")!!

        text_fact_detail.text = factText
        text_date_detail.text = prepareDateToPrint(factDate)
    }

    companion object {
        /**
         * A function to parse the date from a format written in val formatter to a readable
         * one in val formatterToReadableDate.
         * @param unformattedDate date string got from the API
         * @return a string with proper formatting
         */
        fun prepareDateToPrint(unformattedDate: String): String {
            val calendar = Calendar.getInstance()
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("pl", "PL"))
            val formatterToReadableDate =
                SimpleDateFormat("dd-MM-yyyy 'at' HH:mm", Locale("pl", "PL"))

            calendar.time = formatter.parse(unformattedDate)!!

            return formatterToReadableDate.format(calendar.time)
        }
    }
}
