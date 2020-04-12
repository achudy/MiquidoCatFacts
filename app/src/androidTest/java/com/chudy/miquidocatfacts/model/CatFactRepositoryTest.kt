package com.chudy.miquidocatfacts.model

import com.chudy.miquidocatfacts.networking.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Testing the Repository getting the handled responses.
 * Crucial to further showing obtained data.
 */
class CatFactRepositoryTest : KoinTest {

    private val catFactRepository: CatFactRepository by inject()
    private val responseHandler: ResponseHandler by inject()
    private lateinit var result : Resource<List<CatFact>>
    private lateinit var result2 : Resource<List<CatFact>>
    private lateinit var invalidCatFactRepository: CatFactRepository

    /**
     * Creating an invalid Retrofit object to later generate an error status.
     */
    @Before
    fun setUp(){
        val invalidRetrofit = Retrofit.Builder()
            .baseUrl("https://invalidUrl")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
        invalidCatFactRepository = CatFactRepository(provideApi(invalidRetrofit), responseHandler)
    }

    /**
     * Asserting that two valid responses have the same statuses.
     */
    @Test
    fun testSuccessStatuses() {
        runBlocking {
            result = catFactRepository.getThirtyRandomCatFacts()
            result2 = catFactRepository.getThirtyRandomCatFacts()
        }
        assertEquals(result.status, result2.status)
    }

    /**
     * Testing that an invalid request generates an error.
     */
    @Test
    fun testInvalidResponse() {
        runBlocking {
            result = invalidCatFactRepository.getThirtyRandomCatFacts()
        }
        assertEquals(result.status, Status.ERROR)
    }

}