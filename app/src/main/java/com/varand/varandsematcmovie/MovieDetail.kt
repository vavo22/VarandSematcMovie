package com.varand.varandsematcmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.room.Room
import com.squareup.picasso.Picasso
import com.varand.varandsematcmovie.Movie.AppDB
import com.varand.varandsematcmovie.Movie.GetDataServicei
import com.varand.varandsematcmovie.Movie.Movie_Entity
import com.varand.varandsematcmovie.Movie.TitleScream
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.Movie_cover
import kotlinx.android.synthetic.main.activity_movie_detail.movie_name
import kotlinx.android.synthetic.main.activity_movie_detail.textView
import kotlinx.android.synthetic.main.movie_list_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = intent.getStringExtra("imdbid")

        var db = Room.databaseBuilder(applicationContext, AppDB::class.java,"MovieDB").build()
        Thread {
            getCurrentData(intent,db)
            CreateDataBase();

        }.start()

        Handler().postDelayed({

        }, 10000)






        tv_Lik.setOnClickListener(
            View.OnClickListener {
                //tv_Lik.setBackgroundResource(R.drawable.loveit)

                Thread {
                    var emp = Movie_Entity();
                    emp.movie_name = movie_name.text.toString()
                    emp.Movie_id = db.moviedio().readMovie().size + 10
                    emp.movie_ImdbId = textView.text.toString()
                    emp.movie_Year = movie_post.text.toString()
                    db.moviedio().SaveMovie(emp)

                    db.moviedio().readMovie().forEach {
                        Log.i("varandtag","${it.movie_name}")
                        Log.i("varandtag","${it.Movie_id}")
                    }

                    db.moviedio().readMoviebyid("1").forEach {
                        Log.i("varandtag","${it.movie_name}")
                    }
                }.start()





            }

        )
    }
    internal fun CreateDataBase()
    {
        Log.d("AfterDatabes","fdsfsdfdsf")
        //var db= Room.databaseBuilder(applicationContext,AppDb::class.java,"BookDB").build()
        Log.d("AfterDatabes","fdsfsdfdsf")
    }

    internal fun getCurrentData(imdb:String ,db:AppDB) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetDataServicei::class.java)
        val call = service.getCurrentWeatherDatai(imdb, "bc3be72e")
        call.enqueue(object : Callback<TitleScream> {
            override fun onResponse(call: Call<TitleScream>, response: Response<TitleScream>) {
                if (response.code() == 200) {
                    // var weatherResponse: List<imdbMovie> = ArrayList()
                    var weatherResponse = response.body()!!
                    val picasso = Picasso.get().load(weatherResponse.Poster).into(Movie_cover);
                    movie_name.text = weatherResponse.Title
                    textView.text = weatherResponse.imdbID
                    textView2.text = "Genre: " + weatherResponse.Genre.toString()
                    Movie_Description.text ="Plot: " +  weatherResponse.Plot
                    movie_rank.text = weatherResponse.imdbRating
                    tv_Actors.text = "Actors: " + weatherResponse.Actors
                    movie_post.text = weatherResponse.Poster
                    //tv_year.text = weatherResponse.Year
                    //var CountMovie = weatherResponse.Poster
                    Thread {
                        Log.d("varandtag",db.moviedio().readMoviebyid(textView.text.toString()).size.toString())
                    }.start()
                }
            }

            //val adapter = MovieAdapter(valList) {
            //  Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            //}
            override fun onFailure(call: Call<TitleScream>, t: Throwable) {
                // weatherData!!.text = t.message
            }
        })
    }




}


