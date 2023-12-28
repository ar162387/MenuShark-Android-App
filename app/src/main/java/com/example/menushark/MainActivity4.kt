package com.example.menushark

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity4 : AppCompatActivity() {

    private lateinit var menuAdapter: MenuAdapter
    val menuList = ArrayList<Menu>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)


        val listView = findViewById<ListView>(R.id.listview)
        val restaurantId = intent.getStringExtra("id")

        // Check if restaurantId is not null
        // Check if restaurantId is not null
        restaurantId?.let {
            // Initialize menuAdapter before calling fetchMenuItems
            menuAdapter = MenuAdapter(this, R.layout.custom_menu_layout, menuList)
            listView.adapter = menuAdapter

            // Fetch menu items for the given restaurant id
            fetchMenuItems(it)
        }





    }

    private fun fetchMenuItems(restaurantId: String) {
        // Assuming DatabaseAccess.getMenu returns a String
        val databaseAccess: DatabaseAccess = DatabaseAccess.getInstance(applicationContext)
        databaseAccess.open()

        // Fetch menu items for the given restaurant id
        val menuItemsString = databaseAccess.getMenu(restaurantId)

        // Split the menu items string into individual lines
        val menuItemsArray = menuItemsString.split("\n")

        // Create a list to hold Menu objects
        val menuItems = mutableListOf<Menu>()

        // Iterate through each line and create Menu objects
        for (menuItem in menuItemsArray) {
            if (menuItem.isNotEmpty()) {
                val parts = menuItem.split(" - ")
                val id = parts[0]
                val category = parts[1]
                val price = parts[2]
                val menu = Menu(id, category, price)
                menuItems.add(menu)
            }
        }

        // Add the fetched menu items to the list
        menuList.clear()
        menuList.addAll(menuItems)

        // Notify the adapter that the data set has changed
        menuAdapter.notifyDataSetChanged()

        // Close the database connection
        databaseAccess.close()
    }



}