package com.example.madproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.madproject.model.MedicinesModel
import com.example.madproject.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etMediName:EditText
    private lateinit var etMediCompany:EditText
    private lateinit var etMediPrice:EditText
    private lateinit var btnSaveData:Button

    private lateinit var dbRef:DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etMediName = findViewById(R.id.etMediName)
        etMediCompany = findViewById(R.id.etMediCompany)
        etMediPrice = findViewById(R.id.etMediPrice)
        btnSaveData = findViewById(R.id.btnSaveData)

        dbRef=FirebaseDatabase.getInstance().getReference("Medicines")

        btnSaveData.setOnClickListener {
            saveMedicinesData()
        }
    }

    private fun saveMedicinesData(){
        //getting values
        val MediName=etMediName.text.toString()
        val MediCompany=etMediCompany.text.toString()
        val MediPrice=etMediPrice.text.toString()

        if(MediName.isEmpty()){
            etMediName.error="Please enter name"
        }

        if(MediCompany.isEmpty()){
            etMediCompany.error="Please enter company"
        }

        if(MediPrice.isEmpty()){
            etMediPrice.error="Please enter price"
        }

        val empId=dbRef.push().key!!

        val medicines = MedicinesModel(MediId,MediName,MediCompany,MediPrice)

        dbRef.child(MediId).setValue(medicines).addOnCompleteListener {
            Toast.makeText(this,"Data inserted Successfully",Toast.LENGTH_LONG).show()

            etMediName.text.clear()
            etMediCompany.text.clear()
            etMediPrice.text.clear()

        }.addOnFailureListener {err->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }

    }
}