# Just Eat Application by Ravi Kaila
This is a mobile application project for Just Eat, created by Ravi Kaila and this is how to run the app safely.
# Prerequisites
Before using and testing this application, there are some prerequisites in order to compile and run the application:
- An android device that has access to the internet and has Google Maps pre-installed or Android Studio Electric Eel | 2022.1.1 Patch 2.
# How to run the application 
In order to run the application please install the apk file called :"APK_fileName". This will give you access to application.

Alternativley, you can access the application by downloading all the current files and opening them within Android Studio. Please note that this application has been made on Android Studio Electric Eel | 2022.1.1 Patch 2 and may not work on other versions. Once installed please run the application using an emulator created in Android Studio. 

Upon accessing the application you will be shown 10 restaurants that are within a default location. If the user wanted to change location, then they would simply need to enter their desired postcode at the top of the screen, in the search view bar and hit enter. Please note that only postcodes are valid and the application will not allow you to enter invalid postcodes. Upon entering the postcode 10 new restaurants will be displayed that are located near the inputted postcode using the API endpoint: https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/{postcode}. If the user selects a restaurant, they will be directed to Google Maps showing the location of the restaurant based on the restaurant's postcode.

# Assessment Criteria
There were multiple objectives that the application completes when running, which are: 
1. Call the given API Endpoint
2. Select the necessary data from the API (name, cuisines, rating - as a number and address)
3. Display the correct data in an appealing way to the user
4. Allow the user to input a desired postcode.

The application successfully completes each one of the assessment criteria.

## Criteria 1

Criteria 1 was completed as the app successfully calls the API endpoint using multiple files. These files are:
- RequestAPI.kt
- ListenerAPI.kt
- InterfaceAPI.kt
  
All three files are being used within the MainActivity.kt and correctly call the API endpoint

## Criteria 2 

Criteria 2 is completed and proof of this is from the folder called API_Models, where inside the folder there is a kotlin file called APIResponse.kt which outlines that app is only interested in a list of Restaurants that the endpoint gives. 

The rest of files within API_Models defines what a restaurant object should contain. These files successfully select the desired data from the API endpoint. In order to correctly identify how to gather the necessary data and understand it, Postman and https://json2csharp.com/code-converters/json-to-pojo was used to correctly identify the necessary data points.

## Criteria 3

RecyclerViews were used in order to complete criteria 3. Multiple files were created in order to satisfy criteria 3, which are:
- activity_main.xml
- restaurant_item.xml
- RestaurantAdapter.kt

These files were created to display the data given by the API in an appealing way. activity_main.xml is the initial layout of how the app would looks like when starting and restaurant_item.xml is a generalised layout of how each item in the list should appear to the user.

The use of RestaurantAdapter.kt is used to display the list of restaurants to the user. The name of the restaurant is displayed at the top in bold then it is followed by the location of the restaurant, which is in bold and underneath the location is a list of it cuisines. In the bottom right of the displayed restaurant is number followed by a gold star, which is meant to denote the rating of the restaurant. The size of the recyclerView is dependent on the size of restaurant list.

The Recyclerview was then used within MainActivity.kt when calling the API endpoint, within the function getRestaurants which takes in an interface called ListenerAPI and a string called postcode. If no postcode was entered then the string would be defaulted to "TW20 0DE". ListenerAPI is an interface of what the functions of getRestaurants should be.

## Criteria 4 

In order to complete criteria 4, SearchViews was used within activity_main.xml and MainActivity.kt. A searchView allows the user to input their desired postcode. In order to make sure that the user has entererd a valid postcode, Regex was used within MainActivity.kt. If the user did not enter a correct postcode, then the app would display to the user that the inputted text is not a valid postcode. When the user inputs a correct postcode, the function fetchRestaurants is used in order to gather 10 restaurants near the given postcode.

# Assumptions and Improvements

When starting this project there were multiple assumptions that were made. One assumption being that the task should be completed within Android Studios. The reason why this assumption was made was due to the nature of the job application and it being a Mobile Engineering Program. This application showcases the skill in mobile engineering as well as the programming language Kotlin using several techniques, such as : RecyclerViews, the use of retrofit, progress dialogs and many others.

Other assumptions that was made at the start of the project was the data points. More specifically what data should be displayed for Cuisines and Rating. The assumption going into this project was that anything within the List of Cuisines should be displayed to the user even though it is not neccisarily a cuisine. An example of this being certain restaurants would have cuisine names called "Deals" or "Low delivery Fee". These types of tags are not neccisarily cuisines, however they are within the cuisines data class, so should they have been displayed? Within the document given there was no explicit instructions to display only cuisines only to display the data class called cuisines, which the application does. 

A similar assumption was made to rating as well. When trying to get the extract the data type from rating, there were multiple variables within the data class Ratings. There was a thought process of displaying everything within the data class of Ratings, however it was decided to only display starRating (which is a number) to the user, as displaying multiple ratings could be percieved as confusing by the user.

Even after the completion of this project, there can be serval improvements to the application. One such being the add use of different API calls. Adding in different API calls can be seen as fairly easy, as all it would require is the change InterfaceAPI and RequestAPI. The changes of these files, would only require the endpoint of the API and could provide a new experience for the user. Another improvement the app can make is the change when a restaurant is clicked. Instead directing the user to the location of the selected restaurant in Google Maps, the application could take the user to the Just Eat website, where the restaurant can be ordered from. This would make the transition of ordering from the selected restaurant easier for the user.
