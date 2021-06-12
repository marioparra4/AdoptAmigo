package unam.fca.adoptamigo.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(
    context, Contrato.Mascotas.NOMBRE_BD, null,
    Contrato.Mascotas.VERSION_BD) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Contrato.Mascotas.CREA_TABLA_MASCOTAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Contrato.Mascotas.BORRA_TABLA_MASCOTAS)
        onCreate(db)
    }

}