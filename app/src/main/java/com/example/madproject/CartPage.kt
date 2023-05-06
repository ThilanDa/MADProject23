package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CartPage : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)

        //connect to OrderedItems
        var btnorder = findViewById<Button>(R.id.btnorder)
        btnorder.setOnClickListener {
            val intent =Intent(this,OrderedItems::class.java)
            startActivity(intent)
        }
    }
}