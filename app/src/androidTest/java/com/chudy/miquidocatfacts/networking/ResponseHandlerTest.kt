package com.chudy.miquidocatfacts.networking

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Testing the ResponseHandler as this is a crucial point for error handling,
 * which was one of the tasks.
 */
class ResponseHandlerTest : KoinTest {
    private val responseHandler: ResponseHandler by inject()
    private val responseBody =
        "{\"key\":[\"sth\"]}".toResponseBody("application/json".toMediaTypeOrNull())
    private val notFoundResponseForHttpException =
        Response.error<HttpException>(404, responseBody)
    private val unauthorizedResponseForHttpException =
        Response.error<HttpException>(401, responseBody)
    private val otherResponseForHttpException =
        Response.error<HttpException>(418, responseBody)

    /**
     * Handling a timeout while reading from a socket.
     */
    @Test
    fun handleSocketTimeoutException() {
        val exception = SocketTimeoutException()
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Please refresh, there was a timeout.")
    }

    /**
     * Host cannot be resolved. Could be because of an absence of internet connection
     * or it could be a DNS fault.
     */
    @Test
    fun handleUnknownHostException() {
        val exception = UnknownHostException()
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Please check Your internet connection.")
    }

    /**
     * Testing a generated 404.
     */
    @Test
    fun handleHttpException1() {
        val exception = HttpException(notFoundResponseForHttpException)
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Not found. Please refresh.")
    }

    /**
     * Testing a generated 401.
     */
    @Test
    fun handleHttpException2() {
        val exception = HttpException(unauthorizedResponseForHttpException)
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Unauthorised. Please refresh.")
    }

    /**
     * Testing a generated 418.
     */
    @Test
    fun handleHttpException3() {
        val exception = HttpException(otherResponseForHttpException)
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Something went wrong. Please refresh.")
    }

    /**
     * Testing a chosen exception that wasn't specified in the ResponseHandler explicitly.
     */
    @Test
    fun handleNullPointerException() {
        val exception = NullPointerException()
        val response = responseHandler.handleException<Any>(exception)
        assertEquals(response.message, "Something went wrong. Please refresh.")
    }

}