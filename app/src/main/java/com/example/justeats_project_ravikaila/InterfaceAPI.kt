package com.example.justeats_project_ravikaila

import com.example.justeats_project_ravikaila.API_Models.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InterfaceAPI {
    @GET("bypostcode/{postcode}")
    fun getPost(
        @Path("postcode") postcode : String,
        @Query("limit") limit : Int
    ) : Call<APIResponse>
}