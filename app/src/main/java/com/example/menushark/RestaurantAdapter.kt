package com.example.menushark

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView

class RestaurantAdapter(var ctx: Context, var ourResource : Int,
                        var Items: ArrayList<Restaurant>) : ArrayAdapter<Restaurant>(ctx , ourResource , Items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(ourResource, null)

        val resName = view.findViewById<TextView>(R.id.resName)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        resName.text = Items[position].name
        ratingBar.rating = Items[position].score.toFloat()



    return view
    }
}