package com.example.menushark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView

class MenuAdapter(var ctx: Context, var ourResource : Int,
                  var Items: ArrayList<Menu>) : ArrayAdapter<Menu>(ctx , ourResource , Items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(ourResource, null)

        val menuItem = view.findViewById<TextView>(R.id.menuItem)
        val price = view.findViewById<TextView>(R.id.price)

        menuItem.text = Items[position].category
        price.text = Items[position].price

        return view
    }



}