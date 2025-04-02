package com.example.justeats_project_ravikaila

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
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


        var requestManager = RequesterAPI(this)
        var recyclerView = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        var progressDialog : ProgressDialog
        var incorrectPostcode : AlertDialog
        recyclerView.layoutManager = LinearLayoutManager(this)
        val searchView = findViewById<SearchView>(R.id.search)
        //Sets up the progress dialog so that it appears when the API is being called
        progressDialog = ProgressDialog(this).apply{
            setTitle("Please wait")
            setMessage("Fetching restaurants")
            setCancelable(false)
            show()
        }
        //Fetch restaurants based on the postcode string inputted and if nothing is inputted then a default postcode is given
        //Restaurants are then placed within the recyclerView
        fun fetchRestaurants(postcode: String = "TW200DE"){
            requestManager.getRestaurants(object: ListenerAPI {
                override fun onFetch(response: APIResponse, message: String) {
                    progressDialog.dismiss()

                    Toast.makeText(this@MainActivity,"Fetched ${response.restaurants.size}",Toast.LENGTH_SHORT).show()
                    val adapter = RestaurantAdapter(response.restaurants, object: RestaurantClickListener{
                        override fun onRestaurantClick(postcode: String) {
                            // Displays postcode on the restaurant clicked
                            val restaurantPostcode = Uri.parse("geo:0,0?q=$postcode")
                            val googleMaps = Intent(Intent.ACTION_VIEW,restaurantPostcode)
                            googleMaps.setPackage("com.google.android.apps.maps")
                            startActivity(googleMaps)
                        }
                    })
                    recyclerView.adapter = adapter


                }

                override fun onError(message: String) {
                    Toast.makeText(this@MainActivity,"Error: $message", Toast.LENGTH_SHORT).show()

                }
            },postcode = postcode)

        }
        // Creates a progressDialog to show the user the API is being called


        fetchRestaurants()

        // Checks if the inputted postcode is valid using Regex
        fun validPostcode(postcode: String): Boolean{
            val regex = Regex("^[A-Z]{1,2}[0-9]{1,2}[A-Z]?[ ]?[0-9][A-Z]{2}$", RegexOption.IGNORE_CASE)
            return regex.matches(postcode)
        }
        //Checks if user's input is a valid postcode and if it is then fetches the restaurants that are close to the postcode
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(postcode: String): Boolean {
                if(validPostcode(postcode)) {
                    progressDialog.show()
                    fetchRestaurants(postcode)

                }
                // If inputted an incorrect postcode, inform the user
                else{
                    incorrectPostcode = AlertDialog.Builder(this@MainActivity)
                        .setTitle("Incorrect Postcode")
                        .setMessage("Please enter a correct postcode within the UK")
                        .setPositiveButton("Got it "){dialog,_ -> dialog.dismiss()}
                        .show()

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