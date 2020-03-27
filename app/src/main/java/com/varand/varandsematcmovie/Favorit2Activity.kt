package com.varand.varandsematcmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.room.Room
import com.varand.varandsematcmovie.Movie.AppDB
import com.varand.varandsematcmovie.Movie.MovieSearchAdapter
import com.varand.varandsematcmovie.Movie.Search
import kotlinx.android.synthetic.main.activity_favorit2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.txterror


class Favorit2Activity : AppCompatActivity() {
    var dataList = ArrayList<Search>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit2)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java,"MovieDB").build()
        Thread {
            dataList.clear()

            db.moviedio().readMovie().forEach {
                dataList.add(
                    Search(
                        it.movie_Year,
                        it.movie_name,
                        "11111",
                        "11111",
                        it.movie_ImdbId
                    )
                )
            }
            Log.d("varandtag",dataList.size.toString())

        }.start()
        Log.d("varandtag",dataList.size.toString())
           recyclerFav.adapter = MovieSearchAdapter(dataList) { //showDetailsPage(it)
        // Log.d("varanddasize2",it)
         val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("imdbid",it)
        startActivity(intent)
        }
        }
    }

