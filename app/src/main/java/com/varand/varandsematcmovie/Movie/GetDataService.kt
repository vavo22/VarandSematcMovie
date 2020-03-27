package com.varand.varandsematcmovie.Movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GetDataService {
    @GET("/?")
    fun getCurrentWeatherData(@Query("s") s: String, @Query("apikey") apikey: String): Call<MovieTitle>

}

interface GetDataServicei {
    @GET("/?")
    fun getCurrentWeatherDatai(@Query("i") i: String, @Query("apikey") apikey: String): Call<TitleScream>

}