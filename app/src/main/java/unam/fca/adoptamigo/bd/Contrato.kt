package unam.fca.adoptamigo.bd

import android.net.Uri
import android.provider.BaseColumns

object Contrato {
     class Mascotas: BaseColumns {
            companion object {
                val VERSION_BD = 1
                val NOMBRE_BD = "mascotas.db"

                val MASCOTAS = 10
                val MASCOTAS_ID = 20

                val TABLA_MASCOTAS = "mascotas"

                val COLUMNA_ID = "_id"
                val COLUMNA_NOMBRE = "nombre"
                val COLUMNA_RAZA = "raza"
                val COLUMNA_EDAD = "edad"
                val COLUMNA_GENERO = "genero"
                val COLUMNA_UBICACION = "ubicacion"
                val COLUMNA_COLOR = "color"
                val COLUMNA_DESCRIPCION = "descripcion"
                val COLUMNA_NOMBRECONTACTO = "nombreContacto"
                val COLUMNA_TELEFONOCONTACTO = "telefono"
                val COLUMNA_CORREOCONTACTO = "correo"


                var AUTORIDAD = "unam.fca.adoptamigo"
                var MASCOTA_URI = Uri.parse( "content://"+
                        AUTORIDAD +"/"+ TABLA_MASCOTAS )

                val CREA_TABLA_MASCOTAS = (
                        "CREATE TABLE " + TABLA_MASCOTAS
                                + " ("+ COLUMNA_ID + " INTEGER primary key autoincrement, "
                                + COLUMNA_NOMBRE + " TEXT not null, "
                                + COLUMNA_RAZA + " TEXT, "
                                + COLUMNA_EDAD + " INTEGER, "
                                + COLUMNA_GENERO + " TEXT, "
                                + COLUMNA_UBICACION + " TEXT, "
                                + COLUMNA_COLOR + " TEXT, "
                                + COLUMNA_DESCRIPCION + " TEXT, "
                                + COLUMNA_NOMBRECONTACTO + " TEXT, "
                                + COLUMNA_TELEFONOCONTACTO + " TEXT, "
                                + COLUMNA_CORREOCONTACTO + " TEXT)"
                        )

                val BORRA_TABLA_MASCOTAS = "DROP TABLE IF EXISTS $TABLA_MASCOTAS"
            }
     }
}