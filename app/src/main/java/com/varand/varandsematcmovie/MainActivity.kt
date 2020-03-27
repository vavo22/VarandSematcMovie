package com.varand.varandsematcmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.varand.varandsematcmovie.Movie.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var weatherData: TextView? = null
    var dataList = ArrayList<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtGotofav.setOnClickListener {
            val intent = Intent(this, Favorit2Activity::class.java)
            startActivity(intent)
        }

        txtGotohome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        btnSearch.setOnClickListener({
            dataList.clear()
            Log.d("varanddasize2",edtsearch.text.toString())
            getCurrentData(edtsearch.text.toString())
            Handler().postDelayed({

                if (dataList.size == 0)
                {
                    txterror.text = "No Found"
                }
                else
                {
                    txterror.text = ""
                }
                Log.d("varanddasize2",dataList.size.toString())

                recyclervarand.adapter = MovieSearchAdapter(dataList) { //showDetailsPage(it)
                    Log.d("varanddasize2",it)
                    val intent = Intent(this, MovieDetail::class.java)
                    intent.putExtra("imdbid",it)
                    startActivity(intent)
               }
            }, 6000)
        })
    }
    fun onclick(name: String) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }

    internal fun getCurrentData(TextString:String):ArrayList<Search> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetDataService::class.java)
        val call = service.getCurrentWeatherData(TextString, "bc3be72e")
        call.enqueue(object : Callback<MovieTitle> {
            override fun onResponse(call: Call<MovieTitle>, response: Response<MovieTitle>) {
                if (response.code() == 200) {
                    // var weatherResponse: List<imdbMovie> = ArrayList()
                    var weatherResponse = response.body()!!
                    var CountMovie = weatherResponse.Search!!.size
                    dataList.clear()
                    for (i in 0..(CountMovie - 1)) {
                        dataList.add(Search(weatherResponse.Search[i].Poster,weatherResponse.Search[i].Title,weatherResponse.Search[i].Type,weatherResponse.Search[i].Year,weatherResponse.Search[i].imdbID))


                    }
                    Log.d("varanddasize",dataList.size.toString())




                }

            }
            //val adapter = MovieAdapter(valList) {
            //  Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            //}
            override fun onFailure(call: Call<MovieTitle>, t: Throwable) {
                Log.d("varanddasizemes",t.message)
                weatherData!!.text = t.message
            }
        })
        return dataList
    }


}

