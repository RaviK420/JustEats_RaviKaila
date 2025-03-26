package com.example.justeats_project_ravikaila.API_Models

data class Restaurant (
    val name : String,
    val cuisines : List<Cuisine>,
    val rating : Rating,
    val address : Address
        )