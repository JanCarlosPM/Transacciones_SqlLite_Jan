package com.example.transacciones_sqllite_jan.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.transacciones_sqllite_jan.modelo.Estudiante

//Mediante la notacion Database se define la base de datos
//se especifica la version, la exportacion del esquema y las entidades a usar
@Database(entities = [Estudiante::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    //Método abstracto que retorna un objeto estudianteDao que es utilizado por el viewModel
    abstract fun estudianteDao(): EstudianteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        //Con esta función crea y mantiene una instancia unica de la base de datos
        //en toda la aplicacion
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
