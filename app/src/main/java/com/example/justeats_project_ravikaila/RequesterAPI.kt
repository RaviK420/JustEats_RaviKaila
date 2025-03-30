package com.example.justeats_project_ravikaila

import android.content.Context
import com.example.justeats_project_ravikaila.API_Models.APIResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RequesterAPI(private val context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRestaurants(listener: ListenerAPI,postcode: String){
        val apiService = retrofit.create(InterfaceAPI::class.java)
        val call = apiService.getPost(
            postcode,
            10
        )
        call.enqueue(object : Callback<APIResponse>{
            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ){
                if(!response.isSuccessful){
                    listener.onError("Error: ${response.message()}")
                    return
                }
                response.body()?.let {
                    listener.onFetch(it, response.message())
                }?: listener.onError("Empty response")
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable){
                listener.onError(t.message ?: "Unknown error")
            }
        })
    }
}