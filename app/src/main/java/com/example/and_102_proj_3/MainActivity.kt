package com.example.and_102_proj_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private lateinit var flixList: MutableList<String>
    private lateinit var titleList: MutableList<String>
    private lateinit var descriptionList: MutableList<String>
    private lateinit var adapter: FlixAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flixList = mutableListOf()
        titleList = mutableListOf()
        descriptionList = mutableListOf()
        val flixRv = findViewById<RecyclerView>(R.id.projRv)
        adapter = FlixAdapter(flixList, titleList, descriptionList)
        flixRv.adapter = adapter
        flixRv.layoutManager = LinearLayoutManager(this)
        getFlixURL()
    }

    fun getFlixURL(){
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY
        client["https://api.themoviedb.org/3/movie/now_playing?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val flixImageArray = json.jsonObject.getJSONArray("results")
                val flixTitleArray = json.jsonObject.getJSONArray("results")
                val flixDescription = json.jsonObject.getJSONArray("results")

                for (i in 0 until flixImageArray.length()) {
                    flixList.add("https://image.tmdb.org/t/p/w500/"+flixImageArray.getJSONObject(i).getString("poster_path"))
                    titleList.add(flixTitleArray.getJSONObject(i).getString("original_title"))
                    descriptionList.add(flixDescription.getJSONObject(i).getString("overview"))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Flix Error", errorResponse)
            }
        }]
    }
}