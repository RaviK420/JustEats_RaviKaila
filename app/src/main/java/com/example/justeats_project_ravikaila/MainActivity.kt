package com.example.justeats_project_ravikaila

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justeats_project_ravikaila.API_Models.APIResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var requestManger = RequesterAPI(this)
        var recyclerView = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        requestManger.getRestaurants(object: ListenerAPI {
            override fun onFetch(response: APIResponse, message: String) {

                Toast.makeText(this@MainActivity,"Fetched ${response.restaurants.size}",Toast.LENGTH_SHORT).show()
                val adapter = RestaurantAdapter(response.restaurants)
                recyclerView.adapter = adapter

            }

            override fun onError(message: String) {
                Toast.makeText(this@MainActivity,"Error: $message", Toast.LENGTH_SHORT).show()

            }
        })

    }
}