package com.example.menushark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity2 : AppCompatActivity() {


    val restaurant = ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val spinnerCity = findViewById<Spinner>(R.id.spinnerCity)
        val spinnerState = findViewById<Spinner>(R.id.spinnerState)
        val spinnerZipcode = findViewById<Spinner>(R.id.spinnerZipcode)
        val button = findViewById<Button>(R.id.button)

        readRestaurantData()

        val uniqueStates = restaurant.map { it.state }.distinct()
        val uniqueCities = restaurant.map { it.city }.distinct()
        val uniqueZipCodes = restaurant.map { it.zipCode }.distinct()

        val statesList = mutableListOf(
            "WA", "TX", "WI", "UT", "MD", "VA", "IL", "MN", "OR", "AL",
            "DC", "VT", "WV", "ID", "WY",  "AR", "PR", "TN"
        )
        val citiesList = uniqueCities.toMutableList()
        val zipCodesList = uniqueZipCodes.toMutableList()

        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statesList)
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerState.adapter = stateAdapter
        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Get the selected state
                val selectedState = parentView?.selectedItem as? String ?: return

                // Filter the list of restaurants based on the selected state
                val filteredRestaurants = restaurant.filter { it.state == selectedState }

                // Extract unique cities and zip codes from the filtered list
                val uniqueCities = filteredRestaurants.map { it.city }.distinct()


                citiesList.clear()
                citiesList.addAll(uniqueCities)



                // Populate city and zip code dropdowns
                val cityAdapter = ArrayAdapter(this@MainActivity2, android.R.layout.simple_spinner_item, citiesList)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCity.adapter = cityAdapter

                val selectedCity = parentView?.selectedItem as? String ?: return


                val uniqueZipCodes = filteredRestaurants.map { it.zipCode }.distinct()

                zipCodesList.clear()
                zipCodesList.addAll(uniqueZipCodes)


                val zipCodeAdapter = ArrayAdapter(this@MainActivity2, android.R.layout.simple_spinner_item, zipCodesList)
                zipCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerZipcode.adapter = zipCodeAdapter

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }


        }
//        // Set up listener for the city dropdown (if needed)
//        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
//                // Implement this if you need to perform actions when a city is selected
//                val selectedCity = parentView?.selectedItem as? String ?: return
//
//                val filteredRestaurants = restaurant.filter { it.state == selectedCity}
//                val uniqueZipCodes = filteredRestaurants.map { it.zipCode }.distinct()
//
//                zipCodesList.clear()
//                zipCodesList.addAll(uniqueZipCodes)
//
//
//                val zipCodeAdapter = ArrayAdapter(this@MainActivity2, android.R.layout.simple_spinner_item, zipCodesList)
//                zipCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                spinnerZipcode.adapter = zipCodeAdapter
//            }
//
//            override fun onNothingSelected(parentView: AdapterView<*>?) {
//                // Do nothing here
//            }
//        }

//        val arrayAdapter = ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , city)
//        spinnerCity.adapter = arrayAdapter
//        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(applicationContext , "Selected City "+city[p2] , Toast.LENGTH_SHORT ).show()
//
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//
//        }
        button.setOnClickListener {
            val selectedState = spinnerState.selectedItem as? String ?: return@setOnClickListener
            val selectedCity = spinnerCity.selectedItem as? String ?: return@setOnClickListener
            val selectedZipCode = spinnerZipcode.selectedItem as? String ?: return@setOnClickListener



            val intent = Intent(this@MainActivity2 , MainActivity3::class.java)
            intent.putExtra("selectedState", selectedState)
            intent.putExtra("selectedCity", selectedCity)
            intent.putExtra("selectedZipCode", selectedZipCode)


            startActivity(intent)
        }

    }
private fun readRestaurantData()
{
    val input = InputStreamReader(assets.open("restaurants.csv"))
    val reader = BufferedReader(input)

    reader.readLine()
    var line : String?
    while(reader.readLine().also{line = it} != null){
        val row : List<String> = line!!.split(",")

        val sampleRestaurant = Restaurant(row[0] , row[1] , row[2], row[3] , row[4] , row[5] )
        restaurant.add(sampleRestaurant)


    }
}

}