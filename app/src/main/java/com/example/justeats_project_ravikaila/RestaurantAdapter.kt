package com.example.justeats_project_ravikaila

import com.example.justeats_project_ravikaila.API_Models.Restaurant
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RestaurantAdapter(private val restaurant: List<Restaurant>): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val restaurantName: TextView = itemView.findViewById(R.id.restaurantName)
        val location: TextView = itemView.findViewById(R.id.restaurantLocation)
        val cuisines: TextView = itemView.findViewById(R.id.restaurantCuisines)
        val restaurantRating :TextView = itemView.findViewById(R.id.restaurantRating)
    }
    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): RestaurantViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item,parent,false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurant[position]
        holder.restaurantName.text = restaurant.name
        holder.location.text = "${restaurant.address.firstLine}, ${restaurant.address.postalCode}"
        holder.cuisines.text = restaurant.cuisines.joinToString(", ") { it.name }
        holder.restaurantRating.text = "${restaurant.rating.starRating.toString()} *"
//        holder.textView.text = "${restaurant.name} is located at ${restaurant.address.firstLine}, ${restaurant.address.postalCode} has a score of ${restaurant.rating.starRating}, and its cuisines are ${restaurant.cuisines}"

    }

    override fun getItemCount(): Int = restaurant.size

}