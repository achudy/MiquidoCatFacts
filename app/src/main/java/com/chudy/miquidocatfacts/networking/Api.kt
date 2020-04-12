package com.chudy.miquidocatfacts.networking

import com.chudy.miquidocatfacts.model.CatFact
import retrofit2.http.GET

/**
 * An interface for the API, has a suspend get function which sends a GET request to the
 * "facts" endpoint.
 * The task requires getting 30 random cat facts, and that's exactly what can be done by
 * requesting then random and parameters "cat" and "30" in "animal_type" and "amount".
 * Not all data is got from the API and then filtered and chosen at random for obvious brevity.
 */
interface Api {
    @GET("facts/random?animal_type=cat&amount=30")
    suspend fun requestThirtyRandomCatFacts(): List<CatFact>
}