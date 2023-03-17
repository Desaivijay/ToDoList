package com.vijay.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.vijay.todolist.databinding.ActivityMainBinding
import com.vijay.todolist.databinding.LayoutListItemBinding

class MainActivity : AppCompatActivity() {

    // View Binding variable
    private lateinit var binding: ActivityMainBinding

    private lateinit var itemList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Arraylist for listview
        itemList = ArrayList<String>()

        // adapter to set with Listview
        val itemAdapter = MyAdapter(itemList)
        binding.itemsList.adapter = itemAdapter

        // add button click event to add text into listview
        binding.addButton.setOnClickListener {
            val newItem = binding.itemEditText.text.toString().trim()
            if (newItem.isNotEmpty()) {
                itemList.add(newItem)
                itemAdapter.notifyDataSetChanged()
                binding.itemEditText.text.clear()
            }
        }
    }
}

// Custom adapter for list view
class MyAdapter(private val itemList: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup): View {
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.ctvItem.text =
            itemList[position]

        binding.ctvItem.setCheckMarkDrawable(R.drawable.uncheck_button)
        binding.ctvItem.isChecked = false

        // CheckedTextView on click listener
        binding.ctvItem.setOnClickListener {
            if (binding.ctvItem.isChecked) {
                binding.ctvItem.setCheckMarkDrawable(R.drawable.uncheck_button)
                binding.ctvItem.isChecked = false
            } else {
                binding.ctvItem.setCheckMarkDrawable(R.drawable.check_button)
                binding.ctvItem.isChecked = true
            }
        }

        // Delete button to delete item from listview
        binding.ibDelete.setOnClickListener {
            itemList.removeAt(position)
            notifyDataSetChanged()
        }
        return binding.root
    }

}
