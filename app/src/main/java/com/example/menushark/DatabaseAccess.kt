package com.example.menushark


import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseAccess private constructor(context: Context) {

    private val openHelper: SQLiteOpenHelper
    private var db: SQLiteDatabase? = null
    var c: Cursor? = null

    init {
        openHelper = DatabaseOpenHelper(context)
    }

    companion object {
        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance!!
        }
    }

    fun open() {
        db = openHelper.writableDatabase
    }

    fun close() {
        db?.let {
            if (it.isOpen) {
                it.close()
            }
        }
    }

                    fun getMenu(id: String): String {
                        val ID = id.toInt()
                        c = db?.rawQuery("SELECT * FROM menu WHERE restaurant_id = ?", arrayOf(id.toString()))
                        Log.d("Query statement", "Executed successfully")

                        val buffer = StringBuffer()
                        c?.use {
                            while (it.moveToNext()) {
                                val restaurantIdValue = it.getString(it.getColumnIndex"restaurant_id"))
                val categoryValue = it.getString(it.getColumnIndex("category"))
                val priceValue = it.getString(it.getColumnIndex("price"))

                Log.d("Move to Next", "Executed successfully - restaurant_id: $restaurantIdValue, category: $categoryValue, price: $priceValue")

                buffer.append("$restaurantIdValue - $categoryValue - $priceValue\n")
            }
        }
        return buffer.toString()
    }

}

