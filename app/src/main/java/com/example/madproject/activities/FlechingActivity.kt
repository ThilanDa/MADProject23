package com.example.madproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madproject.R
import com.example.madproject.adapters.medicineadapter
import com.example.madproject.model.MedicinesModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import java.text.FieldPosition

class FlechingActivity : AppCompatActivity() {

    private lateinit var mediRecycleView:RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var medicinesList:ArrayList<MedicinesModel>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fleching)

        mediRecylerView = findViewBuId(R.id.rvMedi)
        mediRecycleView.layoutManager=LinearLayoutManager(this)
        mediRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoardingData)

        medicinesList= arrayListOf<MedicinesModel>()

        getMedicinesData()

    }

    private fun getMedicinesData(){
        mediRecycleView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef:FirebaseDtabase.getInstance().getReference("Medicines")

        dbRef:addValueEventListner(object:ValueEventListner{
            override fun onDataChange(snapshot: DataSnapshot){
                medicinesList.clear()
                if(snapshot.exists()){
                    for(medicinesSnap in snapshot.children){
                        val medicinesData=medicinesSnap.getValue(
                            MedicinesModel::class.java
                                    )
                        medicinesList.add(MedicinesData!!)
                    }
                }

                val adapter=medicineadapter(medicinesList)
                mediRecycleView.adapter=mAdapter

                mAdapter.setOnItemListener(object:medicineadapter.onClickListener{
                    override fun onItemClick(position: Int){
                        val intent=intent(this@FlechingActivity,MedicineDetailsActivity::class.java)

                        //put extras
                        intent.putExtra("MediId",medicinesList[position].MediId)
                        intent.putExtra("MediName",medicinesList[position].MediName)
                        intent.putExtra("MediCompany",medicinesList[position].MediCompany)
                        intent.putExtra("MediPrice",medicinesList[position].MediPrice)
                        startActivity(intent)
                    }

                })

                mediRecycleView.visibility = View.VISIBLE
                tvLoadingData.visibility =View.Gone

              }


        })
    }
}