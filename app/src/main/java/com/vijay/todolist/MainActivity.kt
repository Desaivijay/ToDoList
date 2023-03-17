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

    private lateinit var binding: ActivityMainBinding

    private lateinit var itemList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = ArrayList<String>()
        var itemAdapter = MyAdapter(itemList)
        binding.itemsList.adapter = itemAdapter

        binding.addButton.setOnClickListener {
            val newItem = binding.itemEditText.text.toString().trim()
            if (newItem.isNotEmpty()) {
                itemList.add(newItem)
                itemAdapter.notifyDataSetChanged()
                binding.itemEditText.text.clear()
            }
        }

        binding.itemsList.setOnItemClickListener { _, _, position, _ ->
            binding.itemsList.setItemChecked(position, true)
        }

        binding.itemsList.setOnItemLongClickListener { _, _, position, _ ->
            itemList.removeAt(position)
            itemAdapter.notifyDataSetChanged()
            true
        }

    }
}

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
        binding.ctvItem.setOnClickListener {
            if (binding.ctvItem.isChecked) {
                binding.ctvItem.setCheckMarkDrawable(R.drawable.uncheck_button)
                binding.ctvItem.isChecked = false
            } else {
                binding.ctvItem.setCheckMarkDrawable(R.drawable.check_button)
                binding.ctvItem.isChecked = true
            }
        }

        binding.ibDelete.setOnClickListener {
            itemList.removeAt(position)
            notifyDataSetChanged()
        }
        return binding.root
    }

}
