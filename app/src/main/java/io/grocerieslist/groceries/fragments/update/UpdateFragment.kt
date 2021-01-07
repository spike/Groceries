package io.grocerieslist.groceries.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.grocerieslist.groceries.R
import io.grocerieslist.groceries.model.Item
import io.grocerieslist.groceries.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.lang.Double.parseDouble

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.update_item_input.setText(args.currentItem.name)
        view.update_unit_input.setText(args.currentItem.unit)
        view.update_quantity_input.setText(removeTrailingZeros(args.currentItem.quantity.toString()))

        view.update_button.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)

        return view
    }
    private fun removeTrailingZeros(number: String): String {
        return number.replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

    private fun updateItem() {
        val itemName = update_item_input.text.toString()
        val itemQuantity = parseDouble(update_quantity_input.text.toString())
        val itemUnit = update_unit_input.text.toString()

        if (inputCheck(itemName, update_quantity_input.text.toString())) {
            val updatedItem = Item(args.currentItem.id, itemName, itemQuantity, itemUnit)
            mItemViewModel.updateItem(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out both the item name and quantity", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(item: String, quantity: String): Boolean {
        return !(TextUtils.isEmpty(item) && TextUtils.isEmpty(quantity))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mItemViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(), "${args.currentItem.name} successfully deleted!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(requireContext(), "Nothing was deleted", Toast.LENGTH_LONG).show()
        }
        builder.setTitle("Delete ${args.currentItem.name}")
        builder.setMessage("Are you sure you want to delete ${args.currentItem.name}?")
        builder.create().show()
    }

}