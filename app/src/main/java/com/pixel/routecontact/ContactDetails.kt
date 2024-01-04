package com.pixel.routecontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pixel.routecontact.databinding.ActivityContactDetailsBinding

class ContactDetails : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getContactData()
        binding.backBtn.setOnClickListener{
            finish()
        }
    }

    private fun getContactData() {
        val contactData: ContactData? = intent.getParcelableExtra("contentDetails")
        if (contactData!=null) {
            with(binding) {
                contactName.text = contactData.name
                contactNumber.append(contactData.phoneNum)
                description.text = contactData.description
            }
        }
    }
}