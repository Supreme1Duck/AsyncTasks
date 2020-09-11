package com.example.myontacts.fragments.Add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myontacts.Model.User
import com.example.myontacts.R
import com.example.myontacts.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.button_Add.setOnClickListener {
            insertDataInDatabase()
        }
        return view
    }

    private fun insertDataInDatabase() {
        val contact = editName.text.toString()
        val holder = editContact.text.toString()
        val whatIsChecked =
            if (RadiobuttonPhone.isChecked == true) R.drawable.ic_baseline_call_24 else R.drawable.ic_baseline_language_24

        if (isEmpty(contact, holder)) {
            val user = User(
                0,
                holder,
                contact,
                whatIsChecked
            )
            mUserModel.addUser(user)
            Toast.makeText(requireContext(), "Succesfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun isEmpty(contact: String, holder: String): Boolean {
        return !(TextUtils.isEmpty(contact) && TextUtils.isEmpty(holder))
    }

}