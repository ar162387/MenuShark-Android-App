package com.example.menushark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity3 : AppCompatActivity() {

    val restaurant = ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Log.d("MainActivity3", "Setting up ListView and Click Listener")
        val listView = findViewById<ListView>(R.id.listview)


        val selectedState = intent.getStringExtra("selectedState")
        val selectedCity = intent.getStringExtra("selectedCity")
        val selectedZipCode = intent.getStringExtra("selectedZipCode")

        readRestaurantData()
        val filteredrestaurant = ArrayList(restaurant.filter {
             it.zipCode == selectedZipCode
        })

       listView.adapter = RestaurantAdapter(this , R.layout.custom_restaurant_layout , filteredrestaurant)


    listView.setOnItemClickListener { adapterView, view, i, l ->
        val selectedId = (listView.adapter as RestaurantAdapter).Items[i].id

        val intent = Intent(this@MainActivity3 , MainActivity4::class.java)
        intent.putExtra("id" , selectedId)
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