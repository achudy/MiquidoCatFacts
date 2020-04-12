package com.chudy.miquidocatfacts.activity

import com.chudy.miquidocatfacts.activity.DetailsActivity.Companion.prepareDateToPrint
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Testing a method that parses dateStrings as obtained from the API to a readable string.
 */
class DetailsActivityTest {

    private val listOfInputDates = listOf(
        "2020-01-01T00:01:01.016Z",
        "2019-02-01T12:01:01.016Z",
        "2018-03-01T14:01:01.016Z",
        "2017-04-01T22:01:01.016Z"
    )
    private val listOfOutputDates = listOf(
        "01-01-2020 at 00:01",
        "01-02-2019 at 12:01",
        "01-03-2018 at 14:01",
        "01-04-2017 at 22:01"
    )

    /**
     * Iterates through 4 example inputs and compares them to how they should be.
     */
    @Test
    fun testFunPrepareDateToPrint() {
        for (i in 0..3) {
            assertEquals(
                prepareDateToPrint(listOfInputDates[i]),
                listOfOutputDates[i]
            )
        }
    }
}