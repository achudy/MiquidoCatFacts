package com.chudy.miquidocatfacts.model

/**
 * Data class with a model response used in the app, only these fields are required.
 * @param _id is an id containing letters and numbers
 * @param text a cat fact from the API
 * @param updatedAt a date in format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
 */
data class CatFact(val _id:String, val text:String, val updatedAt:String)