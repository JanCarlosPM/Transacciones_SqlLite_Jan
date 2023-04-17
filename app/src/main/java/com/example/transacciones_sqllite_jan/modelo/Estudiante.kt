package com.example.transacciones_sqllite_jan.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

//NOTACION QUE INDICA QUE ESTA CLASE REPRESENTA UNA ENTIDAD EN LA BASE DE DATOS
//LA PROPIEDAD TABLE NAME ESTABLECE EL NOMBRE DE LA TABLA//
@Entity(tableName = "estudiantes")
data class Estudiante(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombres: String,
    val apellidos: String,
    val carrera: String,
    val anio: Int
)
