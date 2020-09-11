package com.example.myontacts.fragments.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myontacts.Model.User
import com.example.myontacts.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.KartinkaKontakta.setImageResource(currentItem.resourceId)
        holder.itemView.holderTextView.text = currentItem.holder
        holder.itemView.contactTextView.text = currentItem.contact

        holder.itemView.ContactConstraint.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()

    }
}
