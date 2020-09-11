package com.example.myontacts.fragments.Update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myontacts.Model.User
import com.example.myontacts.R
import com.example.myontacts.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_Name.setText(args.currentUser.holder)
        view.update_Contact.setText(args.currentUser.contact)

        view.button_Update.setOnClickListener {
            updateBtn()
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun updateBtn() {
        val holderName = update_Name.text.toString()
        val contact = update_Contact.text.toString()

        if (isEmpty(holderName, contact)) {
            val updatedUser =
                User(args.currentUser.id, holderName, contact, args.currentUser.resourceId)
            mUserViewModel.udpateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated succesfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEmpty(contact: String, holder: String): Boolean {
        return !(TextUtils.isEmpty(contact) && TextUtils.isEmpty(holder))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Succesfully removed :${args.currentUser.holder}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.holder}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.holder}")
        builder.create().show()
    }

}