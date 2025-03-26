package com.example.justeats_project_ravikaila

import com.example.justeats_project_ravikaila.API_Models.APIResponse
import retrofit2.Response

interface ListenerAPI {
    fun onFetch(response: APIResponse, message : String)
    fun onError(message: String)
}