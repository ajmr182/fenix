package com.eve.fenix.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eve.fenix.R
import com.eve.fenix.databinding.ItemTaskBinding

class CustomAdapter(private val dataSet: List<ToDoTask>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_task, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemTaskBinding.bind(view)

        fun bind(toDoTask: ToDoTask) {
            binding.textView.text = toDoTask.task
        }
    }
}