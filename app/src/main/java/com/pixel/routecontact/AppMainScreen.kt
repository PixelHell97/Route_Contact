package com.pixel.routecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.pixel.routecontact.databinding.ActivityAppMainScreenBinding

class AppMainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityAppMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private lateinit var adaptor: ContactAdaptor
    private lateinit var contactList: MutableList<ContactData>
    private lateinit var name: String
    private lateinit var phoneNum: String
    private lateinit var desc: String
    private fun initRecyclerView() {
        contactList = mutableListOf()
        adaptor = ContactAdaptor(contactList)
        binding.recycler.adapter = adaptor
        adaptor.onItemClickListener = ContactAdaptor.OnClickListener { position, _ ->
            run {
                val intent = Intent(this, ContactDetails::class.java)
                intent.putExtra("contentDetails", contactList[position])
                startActivity(intent)
            }
        }
    }

    fun saveContact(v: View) {
        desc = binding.description.text.toString()
        if (!isValidName() || !isValidNumber()) {
            return
        }
        contactList.add(ContactData(name, phoneNum, desc))
        binding.contactName.text?.clear()
        binding.contactNumber.text?.clear()
        binding.description.text?.clear()
        Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show()
    }
    private fun isValidName(): Boolean{
        name = binding.contactName.text.toString()
        return if (name.length < 3) {
            binding.contactNameLayout.error = "Not valid name"
            false
        }else {
            binding.contactNameLayout.error = null
            true
        }
    }
    private fun isValidNumber(): Boolean{
        phoneNum = binding.contactNumber.text.toString()
        return if (phoneNum.length < 10) {
            binding.contactNumberLayout.error = "Please enter 10 numbers"
            false
        } else {
            binding.contactNumberLayout.error = null
            true
        }
    }
}