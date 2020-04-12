package com.chudy.miquidocatfacts.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chudy.miquidocatfacts.R
import com.chudy.miquidocatfacts.model.CatFactAdapter
import com.chudy.miquidocatfacts.networking.Status
import com.chudy.miquidocatfacts.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main activity. Shows a list of 30 cat image-id pairs.
 */
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    /**
     * When activity created, a showTheCatFacts function is invoked, as well as the same function
     * can be called from the refresh button as it has a set listener on it.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showTheCatFacts()
        image_refresh.setOnClickListener {
            showTheCatFacts()
        }
    }

    /**
     * A function that loads data from a viewModel and observes its changes.
     * When the loaded resource's status is LOADING, it displays a circular progress,
     * when status is ERROR, it shows a Toast with a message and changes the refresh
     * button's color to red.
     * When an error occurs before loading any data, a placeholder text is displayed.
     */
    private fun showTheCatFacts() {
        mainViewModel.loadData().observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    progress_circular.visibility = View.VISIBLE
                    text_error.visibility = View.GONE
                    colorRefresh(false)
                }
                Status.ERROR -> {
                    progress_circular.visibility = View.GONE
                    colorRefresh(true)
                    if (recycler_facts.isEmpty()) {
                        text_error.visibility = View.VISIBLE
                    }
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    progress_circular.visibility = View.GONE
                    text_error.visibility = View.GONE
                    colorRefresh(false)
                    val listOfObtainedCatFacts = resource.data
                    recycler_facts.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        adapter = CatFactAdapter(context, listOfObtainedCatFacts!!)
                    }
                }
            }
        })

    }

    /**
     * Function to change the refresh button's color between red and black.
     * @param changeToRed true if wanting red, false if black
     */
    private fun colorRefresh(changeToRed: Boolean) {
        if (changeToRed) {
            image_refresh.setColorFilter(
                ContextCompat.getColor(applicationContext, R.color.color_red),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        } else {
            image_refresh.setColorFilter(
                ContextCompat.getColor(applicationContext, R.color.color_black),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }
}