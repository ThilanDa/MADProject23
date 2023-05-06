package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomePage : AppCompatActivity() {



    val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connect to description

        //connect to cartpage
        var btnaddtocart = findViewById<Button>(R.id.btnaddtocart)
        btnaddtocart.setOnClickListener {
            val intent =Intent(this,CartPage::class.java)
            startActivity(intent)
        }

        //connect to registerpage
        var btnregister = findViewById<Button>(R.id.btnregister)
        btnregister.setOnClickListener {
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }




    }
}
