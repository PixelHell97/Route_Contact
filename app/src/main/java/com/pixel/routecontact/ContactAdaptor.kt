package com.pixel.routecontact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.routecontact.databinding.ContactItemBinding

class ContactAdaptor(private val contacts: List<ContactData>?) :
    RecyclerView.Adapter<ContactAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts?.get(position)
        with(holder) {
            binding.contactName.text = contact?.name
            binding.contactNumber.append(contact?.phoneNum)
        }
        if (onItemClickListener!= null){
            holder.itemView.setOnClickListener{
                onItemClickListener?.onItemClick(position, contacts!![position])
            }
        }
    }

    override fun getItemCount(): Int = contacts?.size ?: 0


    var onItemClickListener: OnClickListener?= null
    fun interface OnClickListener {
        fun onItemClick(position: Int, item: ContactData)
    }

    class ViewHolder(val binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: String = binding.contactName.toString()
        val phoneNum: String = binding.contactNumber.toString()
    }
}