# Just Eat Application by Ravi Kaila
This is a mobile application project for Just Eat, created by Ravi Kaila and this is how to run the app safely.
# Prerequisites
Before using and testing this application, there are some prerequisites in order to compile and run the application:
- An Android device that has access to the Internet.
- Have Google Maps installed.
  
Alternatively, download "Android Studio Electric Eel | 2022.1.1 Patch 2".
# How to run the application 
In order to run the application, please install the apk file called "JustEat_Project_RaviKaila.apk" on your Android device. Once installed, open the "JustEat_Project_RaviKaila" application.

Alternatively, you can access the application by downloading the zip file of the repository, extracting the zip and opening the extracted folder within Android Studio. Please note that this application has been made on "Android Studio Electric Eel | 2022.1.1 Patch 2", and may not work on other versions. Once installed, please run the application using an emulator created in Android Studio that has Google Maps installed. 

Upon accessing the application, you will be shown 10 restaurants that are within a default location. If the user wants to change location, they can enter their desired postcode in the search bar at the top of the screen, and hit enter. Please note postcodes are the only form of location that can be entered, and you cannot enter invalid postcodes. Upon entering the postcode, 10 new restaurants will be displayed that are located near the inputted postcode using the API endpoint: https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/{postcode}. If the user selects a restaurant, they will be directed to Google Maps showing the location of the restaurant based on the restaurant's location.

# Assessment Criteria
There were multiple objectives that the application completes when running, which are: 
1. Call the given API Endpoint
2. Select the necessary data from the API (name, cuisines, rating - as a number and address)
3. Display the correct data in an appealing way to the user
4. Allow the user to input a desired postcode

The application successfully completes each one of the assessment criteria.

## Criteria 1

The app successfully calls the API endpoint using multiple files. These files are:
- RequestAPI.kt
- ListenerAPI.kt
- InterfaceAPI.kt
- APIResponse.kt

The above files build and execute the API within "MainActivity.kt".

## Criteria 2 

Within the "API_Models" folder, there is a Kotlin file called "APIResponse.kt" which reduces the amount of data given into a list of restaurant objects. 

The rest of the files within "API_Models" defines what a restaurant object should contain. These files successfully select the desired data from the API endpoint. In order to correctly identify how to gather the necessary data and understand it, [Postman](https://www.postman.com) and [json2csharp](https://json2csharp.com/code-converters/json-to-pojo) was used to correctly identify the necessary data points.

## Criteria 3

RecyclerViews were used in order to complete criteria 3. The files used to implement the RecyclerView are:
- activity_main.xml
- restaurant_item.xml
- RestaurantAdapter.kt

These files were created to display the data given by the API in an appealing way. "restaurant_item.xml" defines the template of how each item in the list should appear to the user.

"RestaurantAdapter.kt" is used to display the list of restaurants to the user. The name of the restaurant is displayed at the top in bold, followed by the location of the restaurant. Underneath this is a list of the restaurant's cuisines. In the bottom right of the card is a number followed by a gold star, which denotes the rating of the restaurant.

The RecyclerView was then used within "MainActivity.kt" when calling the API endpoint, within the "getRestaurants" function, which contains the arguments: an Interface called "ListenerAPI" and a String called "postcode". If no postcode was entered then the String value would be defaulted to "TW20 0DE". "ListenerAPI" details what functions should be within "getRestaurants".

## Criteria 4 

In order to complete criteria 4, a SearchView was used within "activity_main.xml" and "MainActivity.kt". A SearchView allows the user to input their desired postcode. In order to make sure that the user has entererd a valid postcode, Regex was used within "MainActivity.kt". If the user did not enter a correct postcode, then the app would inform the user that the inputted text is not a valid postcode. When the user inputs a correct postcode, the function "fetchRestaurants" gathers 10 restaurants near the given postcode.

# Assumptions and Improvements

When starting this project there were multiple assumptions that were made. One assumption being that the task should be completed within Android Studios. The reason why this assumption was made was due to the nature of the job application and it being a Mobile Engineering Program. This application showcases the skill in Mobile Engineering as well as the programming language Kotlin using several techniques, such as RecyclerViews, the use of Retrofit, progress dialogs and many others.

Other assumptions that were made at the start of the project were the data points. More specifically what data should be displayed for Cuisines and Rating. The assumption going into this project was that anything within the list of Cuisines should be displayed to the user even though it is not necessarily a cuisine. An example of this being certain restaurants would have cuisine names called "Deals" or "Low Delivery Fee". These types of tags are not necessarily cuisines, however they are within the Cuisines data class. Within the document, there was no explicit instructions to display these types of cuisines, so the application operates under the assumption of displaying every String within the Cuisines data class.

A similar assumption was made to rating as well. When trying to extract the data from rating, there were multiple variables within the data class Ratings: count, starRating and userRating. A possibile outcome of the application was to display everything within the data class of Ratings. However, it was decided to only display starRating (which is a number) to the user, as displaying multiple ratings could be perceived as confusing by the user.

Even after the completion of this project, there can be several improvements to the application, one example being the implementation different API calls. Adding in different API calls can be seen as fairly easy, as all it would require is changing the "InterfaceAPI" and "RequestAPI" files. This would only require changing the endpoint of the API and could provide a new experience for the user. Another improvement the app can make is changing the behaviour when clicking the restaurant. Instead of directing the user to the location of the selected restaurant in Google Maps, the application could take the user to the Just Eat website, where the restaurant can be ordered from. This would make the transition of ordering from the selected restaurant easier for the user.
