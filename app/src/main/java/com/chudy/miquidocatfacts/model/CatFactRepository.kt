package com.chudy.miquidocatfacts.model

import com.chudy.miquidocatfacts.networking.Api
import com.chudy.miquidocatfacts.networking.Resource
import com.chudy.miquidocatfacts.networking.ResponseHandler
import org.koin.dsl.module
import retrofit2.HttpException

val catFactModule = module {
    factory { CatFactRepository(get(), get()) }
}

/**
 * A repository class which gets in its constructor an api and a response handler
 * to have a function of getting data from the API and handling it right away.
 */
open class CatFactRepository(
    private val api: Api,
    private val responseHandler: ResponseHandler
    ) {

    /**
     * The function calls for a resource and handles it with a success when there are no errors,
     * catches any exception and handles it appropriately.
     */
    suspend fun getThirtyRandomCatFacts(): Resource<List<CatFact>> {
        return try {
            val response = api.requestThirtyRandomCatFacts()
            responseHandler.handleSuccess(response)
        } catch (e: Exception){
            when(e){
                is HttpException -> responseHandler.handleException(e.code())
                else -> responseHandler.handleException(e)
            }
        }
    }

}