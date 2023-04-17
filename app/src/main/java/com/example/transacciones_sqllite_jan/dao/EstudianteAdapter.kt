package com.example.transacciones_sqllite_jan.dao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.transacciones_sqllite_jan.databinding.EstudianteItemBinding
import com.example.transacciones_sqllite_jan.modelo.Estudiante

class EstudianteAdapter(private val onItemClick: (Estudiante) -> Unit) :
    RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    private var estudiantes = listOf<Estudiante>()

    fun setEstudiantes(estudiantes: List<Estudiante>) {
        this.estudiantes = estudiantes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val binding = EstudianteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EstudianteViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        holder.bind(estudiantes[position])
    }

    override fun getItemCount(): Int = estudiantes.size

    inner class EstudianteViewHolder(private val binding: EstudianteItemBinding, private val onItemClick: (Estudiante) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(estudiante: Estudiante) {
            binding.tvNombreCompleto.text = "${estudiante.nombres} ${estudiante.apellidos}"
            binding.tvCarrera.text = estudiante.carrera
            binding.tvAnio.text = estudiante.anio.toString()

            binding.root.setOnClickListener { onItemClick(estudiante) }
        }
    }
}
