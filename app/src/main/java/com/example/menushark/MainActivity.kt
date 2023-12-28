package com.example.menushark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Loc = findViewById<TextView>(R.id.textView2)

        Loc.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)

            // Start MainActivity2
            startActivity(intent)
        }

    }
}