package com.example.justeats_project_ravikaila

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.Manifest
import android.util.Log
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justeats_project_ravikaila.API_Models.APIResponse
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var requestManger = RequesterAPI(this)
        var recyclerView = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val searchView = findViewById<SearchView>(R.id.search)
        fun fetchRestaurants(postcode: String = "TW200DE"){
            requestManger.getRestaurants(object: ListenerAPI {
                override fun onFetch(response: APIResponse, message: String) {

                    Toast.makeText(this@MainActivity,"Fetched ${response.restaurants.size}",Toast.LENGTH_SHORT).show()
                    val adapter = RestaurantAdapter(response.restaurants)
                    recyclerView.adapter = adapter
                    println(response.restaurants)

                }

                override fun onError(message: String) {
                    Toast.makeText(this@MainActivity,"Error: $message", Toast.LENGTH_SHORT).show()

                }
            },postcode = postcode)

        }
        fetchRestaurants()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fetchRestaurants(it.lowercase())
                    searchView.setQuery("",false)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: Live search here
                return true
            }
        })








    }
}