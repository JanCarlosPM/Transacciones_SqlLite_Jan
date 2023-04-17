package com.example.transacciones_sqllite_jan.modelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.transacciones_sqllite_jan.dao.AppDatabase
import com.example.transacciones_sqllite_jan.dao.EstudianteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EstudianteViewModel(application: Application) : AndroidViewModel(application) {

    private val estudianteDao: EstudianteDao

    //Acá se inicializa un objeto de tipo EstudianteDao obtenido de la instancia AppDatabase
    //en el método init

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        estudianteDao = database.estudianteDao()
    }

    //Acá se crean las funciones, la cuales utilizan el objeto estudianteDao
    //Además se utilizan el suspend indica que la función puede ser suspendida en cualquier momento,
    //para luego ser reanudada en otro momento

    suspend fun insertar(estudiante: Estudiante) = withContext(Dispatchers.IO) {
        estudianteDao.insertar(estudiante)
    }

    suspend fun actualizar(estudiante: Estudiante) = withContext(Dispatchers.IO) {
        estudianteDao.actualizar(estudiante)
    }

    suspend fun eliminar(estudiante: Estudiante) = withContext(Dispatchers.IO) {
        estudianteDao.eliminar(estudiante)
    }

    //Acá se define un objeto LiveData llamado todos el cual es obtenido medinate la funcion obtenerTodos
    //de estudianteDao, utilizado para observar y obtener la lista de todos los estudiantes almacenados
    //en la base de datos en tiempo real

    val todos: LiveData<List<Estudiante>> = estudianteDao.obtenerTodos()
}
