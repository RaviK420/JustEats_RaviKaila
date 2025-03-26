package com.example.justeats_project_ravikaila

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.justeats_project_ravikaila.API_Models.APIResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.Textview)

        var requestManger = RequesterAPI(this)

        requestManger.getRestaurants(object: ListenerAPI {
            override fun onFetch(response: APIResponse, message: String) {
                Toast.makeText(this@MainActivity,"Fetched ${response.restaurants.size}",Toast.LENGTH_SHORT).show()
                val restaurants = response.restaurants
                textView.text = restaurants.joinToString("\n"){it.name}
            }

            override fun onError(message: String) {
                Toast.makeText(this@MainActivity,"Error: $message", Toast.LENGTH_SHORT).show()

            }
        })

    }
}