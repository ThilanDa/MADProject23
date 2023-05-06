package com.example.madproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.madproject.model.MedicinesModel
import com.google.firebase.database.FirebaseDatabase
import java.sql.RowId

class MedicineDetailsActivity : AppCompatActivity() {

    private lateinit var tvMediId: TextView
    private lateinit var tvMediName: TextView
    private lateinit var tvMediCompany: TextView
    private lateinit var tvMediPrice: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("MediId").toString(),
                intent.getStringExtra("MediName").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord (
                intent.getStringExtra("MediId").toString()
                    )
        }

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Medicines").child(id)
        val mTask =dbRef.removeValue()
        
        mTask.addOnSuccessListener { 
            Toast.makeText(this,"Medicine Data Deleted",Toast.LENGTH_LONG).show()
            
            val intent = intent(this,FlechingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Deleting Err ${error.massage}",Toast.LENGTH_LONG).show()
        }
    }

    private fun init View(){...}

    private fun setValuesToViews(){
        tvMediId.text = intent.getStringExtra("MediId")
        tvMediName.text = intent.getStringExtra("MediName")
        tvMediCompany.text = intent.getStringExtra("MediCompany")
        tvMediPrice.text = intent.getStringExtra("MediPrice")
    }

    private fun openUpdateDialog(
        MediId: String,
        MediName:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)
        
        mDialog.setView(mDialogView)
        
        val etMediName = mDialogView.findViewById<EditText>(R.id.etMediName)
        val etMediCompany = mDialogView.findViewById<EditText>(R.id.etMediCompany)
        val etMediPrice = mDialogView.findViewById<EditText>(R.id.etMediPrice)
        val btnUpdateButton = mDialogView.findViewById<EditText>(R.id.btnUpdateData)

        etMediName.setText(intent.getStringExtra("MediName").toString())
        etMediCompany.setText(intent.getStringExtra("MediCompany").toString())
        etMediPrice.setText(intent.getStringExtra("MediPrice").toString())

        mDialog.setTitle("Updating $MediName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateMediData(
                MediId,
                etMediName.text.toString(),
                etMediCompany.text.toString(),
                etMediPrice.text.toString()
            )
        }
        Toast.makeText(applicationContext,"Medicine Data Updated",Toast.LENGTH_LONG).show()

        //setting update data to our textviews
        tvMediName.text = etMediName.text.toString()
        tvMediCompany.text = etMediCompany.text.toString()
        tvMediPrice.text = etMediPrice.text.toString()

        alertDialog.dissmiss()
    }
    private fun updateMediData(
        id:String,
        name:String,
        company:String,
        price:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Medicines").child(id)
        val mediInfo = MedicinesModel(id,name,company,price)
        dbRef.setValue(mediInfo)
    }
}