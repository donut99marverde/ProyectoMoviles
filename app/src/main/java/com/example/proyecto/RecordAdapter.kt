package com.example.proyecto

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.HabitosMainRecordsBinding

class RecordAdapter (var faltantes : List<ListElement> ) : RecyclerView.Adapter<RecordAdapter.ViewHolder>(){
    class ViewHolder (val binding: HabitosMainRecordsBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HabitosMainRecordsBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            nameHabito.text = faltantes[position].nombre_habito
            fechaTextView.text = "Fecha: " + faltantes[position].completo
            iconHabito.setImageDrawable(root.resources.getDrawable(faltantes[position].habitoIcono))
        }
    }

    override fun getItemCount(): Int {
        return faltantes.size
    }


}