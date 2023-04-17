package com.example.transacciones_sqllite_jan.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.transacciones_sqllite_jan.modelo.Estudiante

// indica que esta clase es una base de datos y especifica la lista de entidades que se incluirán en la base de datos,
// así como la versión actual de la base de datos.
@Database(entities = [Estudiante::class], version = 1)
abstract class EstudiantesDatabase : RoomDatabase() {
    //Creamos un método abstracto para obtener un objeto Dao de la entidad de Estudiante
    abstract fun estudianteDao(): EstudianteDao
}
