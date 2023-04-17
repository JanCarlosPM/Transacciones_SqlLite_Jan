package com.example.transacciones_sqllite_jan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.transacciones_sqllite_jan.dao.EstudianteAdapter
import com.example.transacciones_sqllite_jan.databinding.ActivityMainBinding
import com.example.transacciones_sqllite_jan.modelo.Estudiante
import com.example.transacciones_sqllite_jan.modelo.EstudianteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: EstudianteViewModel by viewModels()
    private lateinit var estudianteAdapter: EstudianteAdapter
    private var estudianteSeleccionado: Estudiante? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estudianteAdapter = EstudianteAdapter { estudiante ->
            estudianteSeleccionado = estudiante
            binding.etNombres.setText(estudiante.nombres)
            binding.etApellidos.setText(estudiante.apellidos)
            binding.etCarrera.setText(estudiante.carrera)
            binding.etAnio.setText(estudiante.anio.toString())
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = estudianteAdapter
        }

        viewModel.todos.observe(this, Observer { estudiantes ->
            estudianteAdapter.setEstudiantes(estudiantes)
        })

        binding.btnInsertar.setOnClickListener {
            if (validarCampos()) {
                val nombres = binding.etNombres.text.toString()
                val apellidos = binding.etApellidos.text.toString()
                val carrera = binding.etCarrera.text.toString()
                val anio = binding.etAnio.text.toString().toIntOrNull() ?: 0

                val estudiante = Estudiante(
                    nombres = nombres, apellidos = apellidos, carrera = carrera, anio = anio
                )
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(estudiante)
                    limpiarCampos()
                }
            }
        }

        binding.btnEditar.setOnClickListener {
            if (validarCampos()) {
                estudianteSeleccionado?.let { estudiante ->
                    val nombres = binding.etNombres.text.toString()
                    val apellidos = binding.etApellidos.text.toString()
                    val carrera = binding.etCarrera.text.toString()
                    val anio = binding.etAnio.text.toString().toIntOrNull() ?: 0

                    val estudianteActualizado = Estudiante(
                        id = estudiante.id,
                        nombres = nombres,
                        apellidos = apellidos,
                        carrera = carrera,
                        anio = anio
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.actualizar(estudianteActualizado)
                        limpiarCampos()
                    }
                }
            }
        }

        binding.btnEliminar.setOnClickListener {
            if (validarCampos()) {
                AlertDialog.Builder(this).setTitle("Confirmación")
                    .setMessage("¿Estás seguro de eliminar este registro?")
                    .setPositiveButton("Sí") { _, _ ->
                        estudianteSeleccionado?.let { estudiante ->
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.eliminar(estudiante)
                                limpiarCampos()
                            }
                        }
                    }.setNegativeButton("No", null).show()
            }
        }
    }

    private fun limpiarCampos() {
        binding.etNombres.text.clear()
        binding.etApellidos.text.clear()
        binding.etCarrera.text.clear()
        binding.etAnio.text.clear()
    }

    private fun validarCampos(): Boolean {
        var valido = true

        val nombres = binding.etNombres.text.toString()
        val apellidos = binding.etApellidos.text.toString()
        val carrera = binding.etCarrera.text.toString()
        val anio = binding.etAnio.text.toString()

        if (nombres.isBlank()) {
            binding.etNombres.setError("Este campo no puede estar vacío")
            valido = false
        }
        if (apellidos.isBlank()) {
            binding.etApellidos.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (carrera.isBlank()) {
            binding.etCarrera.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (anio.isBlank()) {
            binding.etAnio.setError("Este campo no puede estar vacío")
            valido = false
        } else if (anio.toIntOrNull() == null) {
            binding.etAnio.setError("Introduzca un número válido")
            valido = false
        }

        return valido
    }
}

