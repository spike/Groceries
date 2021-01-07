package io.grocerieslist.groceries.fragments.list

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.grocerieslist.groceries.R
import io.grocerieslist.groceries.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        mItemViewModel.readAllData.observe(viewLifecycleOwner, Observer { item ->
            adapter.setData(item)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)
        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItems() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mItemViewModel.deleteAllItems()
            Toast.makeText(requireContext(), "Everything was successfully deleted!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(requireContext(), "Nothing was deleted", Toast.LENGTH_LONG).show()
        }
        builder.setTitle("Delete everything")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}