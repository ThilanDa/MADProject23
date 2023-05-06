package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import com.example.madproject.databinding.ActivityMainBinding
import com.example.madproject.databinding.SignupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ktx.childEvents

class MainActivity : AppCompatActivity() {
    private lateinit var binding : SignupBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.cardportal)
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener{

            val name = binding.entername.text.toString()
            val email = binding.enteremail.text.toString()
            val number = binding.contact.text.toString()
            val dob = binding.dob.text.toString()

            val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
            val User = User(name,email,number,dob)
            database.child(name).setValue(User).addOnCanceledListener {
                binding.entername.text.clear()
                binding.enteremail.text.clear()
                binding.contact.text.clear()
                binding.dob.text.clear()

                Toast.makeText(this,"Successfully Saved!",Toast.LENGTH_SHORT).show()

            }
        }


    }
}