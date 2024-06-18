package com.kalinasoft.stretchtimer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter (private val dataSet: Array<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.exercise_name)
        val button: Button = view.findViewById(R.id.action_start)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].name
        holder.button.setOnClickListener {
            val bundle = bundleOf("exercise" to dataSet[position])
            it.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }
}