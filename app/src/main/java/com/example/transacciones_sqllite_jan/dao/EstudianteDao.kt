package com.example.transacciones_sqllite_jan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.transacciones_sqllite_jan.modelo.Estudiante

@Dao //funciona para marcarla como una clase con acceso a data
interface EstudianteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(estudiante: Estudiante): Long

    @Update
    fun actualizar(estudiante: Estudiante): Int

    @Delete
    fun eliminar(estudiante: Estudiante): Int

    //Acá especificamos una consulta personalizada para obtener los registros de la tabla
    //y devuelve un LiveData
    @Query("SELECT * FROM estudiantes")
    fun obtenerTodos(): LiveData<List<Estudiante>>
}
