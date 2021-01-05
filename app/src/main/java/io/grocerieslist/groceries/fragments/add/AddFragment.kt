package io.grocerieslist.groceries.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.grocerieslist.groceries.R
import io.grocerieslist.groceries.data.Item
import io.grocerieslist.groceries.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.add_button.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val item = add_item_input.text.toString()
        val quantity = add_quantity_input.text.toString()

        if (inputCheck(item, quantity)) {
            val item = Item( 0, item, Integer.parseInt(quantity.toString()))

            mItemViewModel.addItem(item)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {

            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(item: String, quantity: String): Boolean {
        return !(TextUtils.isEmpty(item) && TextUtils.isEmpty(quantity))
    }
}