package com.varand.varandsematcmovie.Movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Movie_Dao {

    @Insert
    fun SaveMovie(movie : Movie_Entity)

    @Query ("select * from Movie_Entity")
    fun readMovie() : List<Movie_Entity>

    @Query ("select * from Movie_Entity where movie_ImdbId = :movie_ImdbId")
    fun readMoviebyid(movie_ImdbId:String) : List<Movie_Entity>

}