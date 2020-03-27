package com.varand.varandsematcmovie.Movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie_Entity {
    @PrimaryKey
    var Movie_id : Int =0

    @ColumnInfo(name = "Movie_ImdbID")
    var movie_ImdbId : String = ""

    @ColumnInfo(name = "Movie_name")
    var movie_name : String = ""
    @ColumnInfo(name = "Movie_Year")
    var movie_Year : String = ""
}