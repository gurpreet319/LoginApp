package com.example.task

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity.*

class secondactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val fruits: Array<String> = resources.getStringArray(R.array.Fruits)
        val Array_adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruits)

        listview.adapter = Array_adapter

        listview.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selectedItem = adapterView.getItemAtPosition(position) as String
                Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()
            }
    }
}