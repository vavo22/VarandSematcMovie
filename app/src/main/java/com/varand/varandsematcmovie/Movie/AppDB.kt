package com.varand.varandsematcmovie.Movie

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [(Movie_Entity::class)],version =1)
abstract class AppDB: RoomDatabase() {
    abstract fun moviedio() : Movie_Dao
}