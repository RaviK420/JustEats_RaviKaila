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
                textView.text = restaurants.joinToString("\n\n"){restaurant ->
                    val name = restaurant.name
                    val cuisines = restaurant.cuisines.joinToString { it.name }
                    val locationAddress = restaurant.address.firstLine
                    val locationPostcode = restaurant.address.postalCode
                    val rating = restaurant.rating.starRating
                    "The rating for $name is $rating, which is located at $locationAddress, $locationPostcode and is $cuisines."

                }
            }

            override fun onError(message: String) {
                Toast.makeText(this@MainActivity,"Error: $message", Toast.LENGTH_SHORT).show()

            }
        })

    }
}