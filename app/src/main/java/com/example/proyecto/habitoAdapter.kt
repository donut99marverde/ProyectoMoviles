package com.example.proyecto

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.HabitosMainFaltantesBinding

class habitoAdapter (var faltantes : List<ListElement> ) : RecyclerView.Adapter<habitoAdapter.ViewHolder>(){
    class ViewHolder (val binding: HabitosMainFaltantesBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): habitoAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HabitosMainFaltantesBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: habitoAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            alarmas.text = faltantes[position].alarma
            nameHabito.text = faltantes[position].nombre_habito
            TextViewEstado.text = faltantes[position].completo
//            iconHabito.drawable = faltantes[position].habitoIcono
            iconHabito.setImageDrawable(root.resources.getDrawable(faltantes[position].habitoIcono))
//            root.resources.getDrawable(faltantes[position].habitoIcono)
        }
    }

    override fun getItemCount(): Int {
        return faltantes.size
    }


}